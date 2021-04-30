<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;

use App\Entity\Specialist;
use App\Form\AjouterspecialistType;
use App\Form\ModifierspecialistType;




use Symfony\Component\Routing\Annotation\Route;

class BackController extends AbstractController
{
    /**
     * @Route("/back", name="back")
     */
    public function index(): Response
    {
        return $this->render('back/index.html.twig', [
            'controller_name' => 'BackController',
        ]);
    }
    /**
     * @Route("/specialistajouter", name="specialistajouter")
     */
    public function ajouter(Request $request):Response
    {
        $specialist = new Specialist();
        $form = $this->createForm(AjouterspecialistType::class, $specialist);

        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {



            $poste= $form->getData();

            $em = $this->getDoctrine()->getManager();
            $em->persist( $specialist);
            $em->flush();

            return $this->redirectToRoute('showspe' );

        }

        return $this->render(
            'back/ajouterspecialist.html.twig',
            array('form' => $form->createView())
        );
    }
    /**
     * @Route("/modifierspecialist/{id}", name="modifierspecialist")
     */
    public function modifier1(Request $request, $id){
        $em = $this->getDoctrine()->getManager();
        $specialist = $em->getRepository('App\Entity\Specialist')->find($id);


        if (!$specialist) {
            throw $this->createNotFoundException(
                'There are no formation with the following id: ' . $id
            );
        }

        /** @var TYPE_NAME $form */
        $form = $this->createForm(ModifierspecialistType::class, $specialist);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {


            $em->flush();


            return $this->redirectToRoute('showspe');


        }

        return $this->render(
            'back/modif.html.twig',
            array('form' => $form->createView())
        );
    }
    /**
     * @Route("/showspe", name="showspe")
     */
    public function showspe()
    {
        $specialist = $this->getDoctrine()
            ->getRepository('App\Entity\Specialist')
            ->findAll();

        return $this->render(
            'back/showspe.html.twig',
            array('specialists' => $specialist)
        );
    }
    /**
     * @Route("/deletespecialist/{id}", name="deletespecialist")
     */
    public function deletespecialist($id)
    {
        $em = $this->getDoctrine()->getManager();
        $specialist = $em->getRepository('App\Entity\Specialist')->find($id);

        if (!$specialist) {
            throw $this->createNotFoundException(
                'There are no articles with the following id: ' . $id
            );
        }

        $em->remove($specialist);
        $em->flush();

        return $this->redirectToRoute('showspe');
    }


}
