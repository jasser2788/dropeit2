<?php

namespace App\Controller;
use App\Entity\Like;
use App\Entity\Poste;
use App\Entity\Commentaire;
use App\Form\CommentaireType;
use App\Form\LikeType;
use App\Repository\CategorieRepository;
use App\Repository\PosteRepository;
use App\Repository\ProgressRepository;
use App\Repository\UtilisateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class CommentaireController extends AbstractController
{
    /**
     * @Route("/commentaire", name="commentaire")
     */
    public function index(): Response
    {
        return $this->render('commentaire/index.html.twig', [
            'controller_name' => 'CommentaireController',
        ]);
    }
    /**
     * @Route("/ajoutercommantaire/{id}", name="commantaire")
     */
    public function ajoutercommantaire(request $request,$id)
    {     $repository=$this->getDoctrine()->getRepository(Poste::class);
        $poste = $repository->find($id);
        $ok=false;
        $comment=new Commentaire();
        $form=$this->createForm(CommentaireType::class,$comment);


        $form->handleRequest($request);

        if($form->isSubmitted()&&$form->isValid()){
            $comment->setDate(new \DateTime());
            $comment->setIdPoste($poste);
            $comment->setIdClient(5);





            $em=$this->getDoctrine()->getManager();

            $em->persist($comment);
            $em->flush();
            //$flashy->success('votre commentaire est ajouté avec succès !' );

            return $this->redirect('/ajoutercommantaire/'.$id);
        }
        $like=new Like();
        $formm=$this->createForm(LikeType::class,$like);


        $formm->handleRequest($request);

        if($formm->isSubmitted()&&$formm->isValid()){

            $like->setIdPoste($poste);
            $like->setIdClient(5);
            $ok=true;




            $em=$this->getDoctrine()->getManager();

            $em->persist($like);
            $em->flush();
            //$flashy->success('votre commentaire est ajouté avec succès !' );

            // return $this->redirect('/ajoutercommantaire/'.$id);


        }






        return $this->render('commentaire/ajoutercommentaire.html.twig', array(
            'poste' => $poste,
            'comment'=>$form->createView(),
            'hou'=>$formm->createView(),
             'ok'=>$ok,

        ));

    }
   /* /**
     * @Route("/likeC/{id}",name="likeC")
     */

}
