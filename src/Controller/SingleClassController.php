<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\DataType;

use Symfony\Component\HttpFoundation\Request;


class SingleClassController extends AbstractController
{
    /**
     * @Route("/front/front", name="front_front")
     */
    public function index(): Response
    {
        return $this->render('single_class/index.html.twig', [
            'controller_name' => 'SingleClassController',
        ]);
    }
    /**
     * @Route("/single/class", name="single_class")
     */
    public function showexe()
    {
        $exercice = $this->getDoctrine()
            ->getRepository('App\Entity\Exercice')
            ->findAll();

        return $this->render(
            'exercice/single_class.html.twig',
            array('exercices' => $exercice)
        );
    }


}
