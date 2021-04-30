<?php

namespace App\Controller;
use App\Entity\Progress;
use App\Form\AjouterposteType;
use App\Form\AjouterweightType;
use App\Form\DataType;
use App\Form\HouyemType;
use App\Form\ModifierposteType;
use App\Repository\ProgressRepository;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Poste;
use App\Entity\Weightin;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\Routing\Annotation\Route;

use App\Form\ModifierformateurType;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use symfony\Component\Serializer\annotaion\groups;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Dompdf\Dompdf;
use Dompdf\Options;

class PosteController extends AbstractController
{
    /**
     * @Route("/poste", name="poste")
     */
    public function index(): Response
    {
        return $this->render('poste/index.html.twig', [
            'controller_name' => 'PosteController',
        ]);
    }
    /**
     * @Route("/posteajouter", name="posteajouter")
     */
   public function ajouter(Request $request):Response
    {
        $poste = new Poste();
        $form = $this->createForm(AjouterposteType::class, $poste);

        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {
            $file=$poste->getImage();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            $poste= $form->getData();

            $em = $this->getDoctrine()->getManager();
            $poste->setImage($filename);
            $em->persist( $poste);
            $em->flush();

           return $this->redirectToRoute('showposte' );

        }

        return $this->render(
            'poste/ajouterposte.html.twig',
            array('form' => $form->createView())
        );
}
    /**
     * @Route("/showposte", name="showposte")
     */
    public function showposte()
    {
        $poste = $this->getDoctrine()
            ->getRepository('App\Entity\Poste')
            ->findAll();

        return $this->render(
            'poste/showposte.html.twig',
            array('postes' => $poste)
        );
    }
    /**
     * @Route("/deleteposte/{id}", name="deleteposte")
     */
    public function deleteposte($id)
    {
        $em = $this->getDoctrine()->getManager();
        $poste = $em->getRepository('App\Entity\Poste')->find($id);

        if (!$poste) {
            throw $this->createNotFoundException(
                'There are no articles with the following id: ' . $id
            );
        }

        $em->remove($poste);
        $em->flush();

        return $this->redirectToRoute('showposte');
    }

    /**
     * @Route("/modifierposte/{id}", name="modifierposte")
     */
    public function modifier1(Request $request, $id){
        $em = $this->getDoctrine()->getManager();
        $poste = $em->getRepository('App\Entity\Poste')->find($id);


        if (!$poste) {
            throw $this->createNotFoundException(
                'There are no formation with the following id: ' . $id
            );
        }

        /** @var TYPE_NAME $form */
        $form = $this->createForm(HouyemType::class, $poste);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $file=$poste->getImage();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $poste->setImage($filename);

            $em->flush();


            return $this->redirectToRoute('showposte');


        }

        return $this->render(
            'poste/modif.html.twig',
            array('form' => $form->createView())
        );
    }
    /**
     * @Route("/ajouterposteF", name="ajouterposteF")
     */
    public function ajouterposteF(Request $request):Response
    {
        $poste = new Poste();
        $form = $this->createForm(AjouterposteType::class, $poste);

        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {
            $file=$poste->getImage();
            $filename = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            $poste= $form->getData();

            $em = $this->getDoctrine()->getManager();
            $poste->setImage($filename);
            $em->persist( $poste);
            $em->flush();

            //return $this->redirectToRoute('afficherposte' );

        }

        return $this->render(
            'postefront/ajouterposteF.html.twig',
            array('form' => $form->createView())
        );
    }
    /**
     * @Route("/showW", name="showW")
     */
    public function ajouterW(Request $request,FlashyNotifier $flashy):Response
    {
        $date = new \DateTime();
        $string = $date->format(DATE_RFC2822);
        $weight= new Weightin();
        $form = $this->createForm(AjouterweightType::class, $weight);

        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {

            $weight->setIdClient(5);
            $weight->setDate($date->format(DATE_RFC2822));
            $weight= $form->getData();

            $em = $this->getDoctrine()->getManager();

            $em->persist( $weight);
            $em->flush();
            $flashy->success('your weight as ben added !' );

            //return $this->redirectToRoute('afficherposte' );
            return $this->redirectToRoute('show');

        }

        return $this->render(
            'postefront/progress2.html.twig',
            array('form' => $form->createView())
        );


    }
    /**
     * @Route("/data", name="data")
     */
    public function ajouterD(Request $request):Response
    {

        $prog= new Progress();
        $form = $this->createForm(DataType::class, $prog);

        $form->handleRequest($request);

        if ($form->isSubmitted()&& $form->isValid()) {

            $prog->setIdClient(5);

            $pro= $form->getData();

            $em = $this->getDoctrine()->getManager();

            $em->persist( $prog);
            $em->flush();

            //return $this->redirectToRoute('afficherposte' );
            return $this->redirectToRoute('show');

        }

        return $this->render(
            'postefront/ajouterdata.html.twig',
            array('form' => $form->createView())
        );


    }
    /**
     * @Route("/show", name="show")
     */
    public function showWeight()
    {
        $weight = $this->getDoctrine()
            ->getRepository('App\Entity\Weightin')
            ->findAll();
        $data = $this->getDoctrine()
            ->getRepository('App\Entity\Progress')
            ->findAll();

        return $this->render(
            'postefront/progress.html.twig',
            array('curentW' => $weight,'data'=>$data)
        );
    }

//    /**
//     * @Route("/show2", name="show2")
//     */
//    public function showWeight2()
//    {
//        $pdfOptions = new Options();
//        $pdfOptions->set('defaultFont', 'Arial');
//
//        // Instantiate Dompdf with our options
//        $dompdf = new Dompdf($pdfOptions);
//        $weight = $this->getDoctrine()
//            ->getRepository('App\Entity\Weightin')
//            ->findAll();
//
//
//
//
//        // Retrieve the HTML generated in our twig file
//        $html = $this->renderView(
//            'postefront/pdfpro.html.twig',
//            array('curentW' => $weight)
//        );
//
//        // Load HTML to Dompdf
//        $dompdf->loadHtml($html);
//
//        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
//        $dompdf->setPaper('A4', 'portrait');
//
//        // Render the HTML as PDF
//        $dompdf->render();
//
//        // Store PDF Binary Data
//        $output = $dompdf->output();
//
//        // In this case, we want to write the file in the public directory
//        $publicDirectory = $this->get('kernel')->getProjectDir() . '/public/doc';
//        // e.g /var/www/project/public/mypdf.pdf
//        $pdfFilepath =  $publicDirectory . '/mypdf.pdf';
//
//        // Write file to the desired path
//        file_put_contents($pdfFilepath, $output);
//
//        // Send some text response
//        return new Response("The PDF file has been succesfully generated !");
//
//        ///////////////////////////////here we go
//
//    }
    /**
     * @Route("/searchposte ", name="searchposte")
     */
    public function searchposte(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Poste::class);
        $requestString = $request->get('searchValue');
        $poste = $repository->findPosteByid($requestString);
        $encoders = [new XmlEncoder(), new JsonEncoder()];
        $normalizers = [new ObjectNormalizer()];

        $serializer = new Serializer($normalizers, $encoders);
        $jsonContent = $serializer->serialize($poste, 'json',[
            'circular_reference_handler' => function ($object) {
                return $object->getId();
            }
        ]);


        $response = new Response(json_encode($jsonContent));
        $response->headers->set('Content-Type', 'application/json; charset=utf-8');

        return $response;

    }


    




}

