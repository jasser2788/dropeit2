<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Entity\User;
use App\Entity\Vote;
use App\Form\ReclamationReponseType;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }

    /**
     * @Route("/reclamationClient", name="reclamationClient", methods={"GET"})
     */
    public function ClientReclamtion(ReclamationRepository $reclamationRepository): Response
    {
        $user = $this->getDoctrine()->getRepository(User::class)->find(1);

        return $this->render('reclamation/indexClient.html.twig', [
        'reclamations' => $reclamationRepository->findBy(array('user'=>$user)),
        ]);
    }

    function filterwords($text){
        $filterWords = array('fuck','bitch' ,'test');
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }


    /**
     * @Route("/new", name="reclamation_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $avis = $this->getDoctrine()->getRepository(Vote::class)->find(1);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamation->setEtat(0);
            $user = $this->getDoctrine()->getRepository(User::class)->find(1);
            $reclamation->setUser($user);
            $reclamation->setDescription($this->filterwords($reclamation->getDescription()));
            $reclamation->setDaterec(new \DateTime());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();

            $sid    = "AC02adfa2792481fb553b4969fff2663f0";
            $token  = "7f2d42ea7ed79a014d67b92e9b244c38";
            $client = new Client($sid, $token);
            $message = $client->messages->create(
                '+21656474153', // Text this number
                [
                    'from' => '+19199481322', // From a valid Twilio number
                    'body' => 'Vous avez recu une nouvelle reclamation !'
                ]);
            return $this->redirectToRoute('reclamationClient');
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'avis'=>$avis,
            'form' => $form->createView(),
        ]);
    }



    /**
     * @Route("/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation)
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }




    /**
     * @Route("/{id}/edit", name="reclamation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reclamation $reclamation): Response
    {
        $form = $this->createForm(ReclamationReponseType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamation->setEtat(1);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_index');
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id}/delete", name="reclamation_delete")
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($reclamation);
        $entityManager->flush();


        return $this->redirectToRoute('reclamation_index');
    }



    /**
     * @Route("/rate", name="rate_", methods={"POST"})
     */
    public function rateAction(\Symfony\Component\HttpFoundation\Request $request){
        $data = $request->getContent();
        $obj = json_decode($data,true);

        $em = $this->getDoctrine()->getManager();
        $rate =$obj['rate'];
        $avis = $em->getRepository(Vote::class)->find(1);
        $note = ($avis->getRate()*$avis->getVote() + $rate)/($avis->getVote()+1);
        $avis->setVote($avis->getVote()+1);
        $avis->setRate($note);
        $em->persist($avis);
        $em->flush();
        return new Response($avis->getRate());
    }
}
