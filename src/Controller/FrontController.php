<?php

namespace App\Controller;

use App\services\QrCodeServices;

use App\Form\SearchType;

use App\Entity\Rendezvous;
use App\Repository\RendezvousRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

use App\Entity\Specialist;
use App\Form\AjouterspecialistType;
use App\Form\ModifierspecialistType;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{
    /**
     * @Route("/front", name="front")
     */
    public function index(): Response
    {
        return $this->render('front/index.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }

    /**
     * @Route ("/showrdv",name="showrdv")
     */
    public function showrdv()
    {
        $specialist = $this->getDoctrine()
            ->getRepository('App\Entity\Specialist')
            ->findAll();

        return $this->render(
            'front/showrdv.html.twig',
            array('specialists' => $specialist)
        );
    }

    /**
     * @Route ("/showmyrdv",name="showmyrdv")
     */
    public function showmyrdv()
    {
        $rendezvous = $this->getDoctrine()
            ->getRepository('App\Entity\Rendezvous')
            ->findAll();

        return $this->render(
            'front/showmyrdv.html.twig',
            array('rdvs' => $rendezvous)
        );
    }

    /**
     * @Route("/ajoutrdv/{id}", name="ajoutrdv")
     */
    public function ajouter(Request $request, $id, \Swift_Mailer $mailer): Response
    {

        $ems = $this->getDoctrine()->getManager();
        $specialist = new Specialist();
        $specialist = $ems->getRepository(Specialist::class)->find($id);


        $em = $this->getDoctrine()->getManager();

        $rendezvous = new Rendezvous();
        $repo = $this->getDoctrine()->getRepository(Rendezvous::class);

        $rendezvous->setIdSpe($id);
        $rendezvous->setNom($specialist->getNom());
        $rendezvous->setPrenom($specialist->getPrenom());

        $rendezvous->setSpecialite($specialist->getSpecialite());
        $rendezvous->setMeet($specialist->getMeet());
        $rendezvous->setDate($specialist->getDate());

        $em->persist($rendezvous);
        $em->flush();
        $ems->remove($specialist);
        $ems->flush();

        $date = $rendezvous->getDate()->format('Y-m-d');
        $spe = $rendezvous->getSpecialite();
        $meet = $rendezvous->getMeet();
        $message = (new \Swift_Message('Seance Réserver'))
            ->setFrom('lolikoppil@gmail.com')
            ->setTo('mohamedjasser.benothman@esprit.tn')
            ->setBody("La seance de $spe du $date a été réserver\nLien meet:$meet "

            );
        $mailer->send($message);
        return $this->redirectToRoute('showrdv');
    }

    /**
     * @Route("/reajoutrdv/{id}", name="reajoutrdv")

     */
    public function reajouter(Request $request,$id, \Swift_Mailer $mailer):Response
    {
        $em = $this->getDoctrine()->getManager();

        $rendezvous = new Rendezvous();
        $rendezvous = $em->getRepository(rendezvous::class)->find($id);



        $ems = $this->getDoctrine()->getManager();
        $specialist = new Specialist();

       // $specialist->setId($rendezvous->getIdSpe());
        $specialist->setPrenom($rendezvous->getPrenom());
        $specialist->setNom($rendezvous->getNom());
        $specialist->setMeet($rendezvous->getMeet());
        $specialist->setSpecialite($rendezvous->getSpecialite());
        $specialist->setDate($rendezvous->getDate());
        $em->persist($specialist);
        $em->flush();
        $ems->remove($rendezvous);
        $ems->flush();

        $date = $rendezvous->getDate()->format('Y-m-d');
        $spe = $rendezvous->getSpecialite();

        $message = (new \Swift_Message('Seance Annuler'))
            ->setFrom('lolikoppil@gmail.com')
            ->setTo('mohamedjasser.benothman@esprit.tn')
            ->setBody("La seance de $spe du $date a été annuler"

            );
        $mailer->send($message);
    return $this->redirectToRoute('showmyrdv');
}
    /**
     * @Route("/qrcodeg/{meet}",name="qrcodeg")
     */
    public function qrgener(Request $request,QrCodeServices $qrcodeServices,$meet): Response
    {

        $qrCode = null;
        //$form = $this->createForm(SearchType::class, null);
     //   $form->handleRequest($request);

       /* if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            $qrCode = $qrcodeServices->qrcode($data['name']);
        }*/
        $qrCode = $qrcodeServices->qrcode($meet);
        return $this->render('front\QrCode.html.twig', [
           // 'form' => $form->createView(),
            'qrCode' => $qrCode
        ]);
        return $this->redirectToRoute('front\QrCode.html.twig');

    }
}