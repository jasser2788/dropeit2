<?php

namespace App\Controller;

use App\Entity\Vote;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TestController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(): Response
    {
        $avis = $this->getDoctrine()->getRepository(Vote::class)->find(1);

        return $this->render('reclamation/showAvis.html.twig', [
            'avis' => $avis,
        ]);
    }
}
