<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class Template2Controller extends AbstractController
{
    /**
     * @Route("/template2", name="template2")
     */
    public function index(): Response
    {
        return $this->render('template2/index.html.twig', [
            'controller_name' => 'Template2Controller',
        ]);
    }
}
