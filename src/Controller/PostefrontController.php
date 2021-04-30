<?php

namespace App\Controller;

use App\Entity\Poste;
use App\Form\AjouterposteType;
use App\Repository\BlogRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PostefrontController extends AbstractController
{
    /**
     * @Route("/postefront", name="postefront")
     */
    public function index(): Response
    {
        return $this->render('postefront/index.html.twig', [
            'controller_name' => 'PostefrontController',
        ]);
    }

    /**
     * @Route ("/afficherposte",name="afficherposte")
     */
    public function showposte()
    {
        $poste = $this->getDoctrine()
            ->getRepository('App\Entity\Poste')
            ->findAll();

        return $this->render(
            'postefront/afficherposte.html.twig',
            array('postes' => $poste)
        );
    }
    /**
     * @Route ("/snap",name="snap")
     */
    public function pic()
    {
        $filename = 'pic_'.date('YmdHis') . '.jpeg';

        $url = '';
       // if( move_uploaded_file($_FILES['webcam']['tmp_name'],'upload/'.$filename) ) {

            $url = 'http://' . $_SERVER['HTTP_HOST'] . dirname($_SERVER['REQUEST_URI']) . '/uploads//' . $filename;
        //}

// Return image url
        echo $url;



        return $this->render(
            'postefront/webcame.html.twig', array('houyem' => $url)

        );
    }
    /**
     * @Route ("/snap2",name="snap2")
     */
    public function pic2(Request $request)
    {
    echo "houhou";
        /*$filename = 'pic_' . date('YmdHis') . '.jpeg';

        $url = '';
        echo $filename ;
        if (move_uploaded_file($_FILES["pic"]["tmp_name"], 'uploads/' . $filename)) {
       //     $url = 'http://' . $_SERVER['HTTP_HOST'] . dirname($_SERVER['REQUEST_URI']) . '/uploads/' . $filename;
      //  }

// Return image url
        echo $url;*/
        return $this->render(
            'postefront/webcame.html.twig', array('houyem')

        );
        if($request->isMethod('POST')){
            $filename = $request->files->get('imageprev');
            echo $filename;
        }

    }
}
