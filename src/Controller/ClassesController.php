<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\DataType;

use Symfony\Component\HttpFoundation\Request;
class ClassesController extends AbstractController
{
    /**
     * @Route("/front", name="front")
     */
    public function index(): Response
    {
        return $this->render('classes/index.html.twig', [
            'controller_name' => 'ClassesController',
        ]);
    }
    /**
     * @Route("/classes", name="classes")
     */
    public function showprog()
    {
        $programme = $this->getDoctrine()
            ->getRepository('App\Entity\Programme')
            ->findAll();

        return $this->render(
            'front/classes.html.twig',
            array('programmes' => $programme)
        );
    }










}
