<?php


namespace App\Controller;


use App\Entity\Evenement;
use App\Entity\Reservation;
use App\Repository\ClientRepository;
use App\Repository\EvenementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Gregwar\CaptchaBundle\Type\CaptchaType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;


class EventController extends AbstractController
{

    /**
     * @var EvenementRepository
     */
    private $evenementRepository;
    /**
     * @var EntityManagerInterface
     */
    private $entityManager;
    /**
     * @var ClientRepository
     */
    private $clientRepository;

    public function __construct(EvenementRepository $evenementRepository, EntityManagerInterface $entityManager, ClientRepository $clientRepository)
    {
        $this->evenementRepository = $evenementRepository;
        $this->entityManager = $entityManager;
        $this->clientRepository = $clientRepository;
    }

    /**
     * @Route("/event", name="event_index", methods={"GET"})
     */

    public function event(){
        $evenemnts  = $this->evenementRepository->findAll();

        return $this->render('evenement/event.html.twig',[
            'evenements' =>$evenemnts
        ]);
    }

    /**
     * @Route("/event/{id}", name="event_reserver", methods={"GET", "POST"})
     */

    public function reserver(Evenement $id, \Swift_Mailer $mailer ) {

        $reservation = new Reservation();
        $this->entityManager->persist($reservation);

        $this->entityManager->flush();
        if ($reservation){
            $evenement = $this->evenementRepository->find($id->getId());
            if ($evenement->getNbpart()>  0 ){
                // $client = $this->getUser()
                $user = $this->clientRepository->find(1);
                $evenement->setNbpart($evenement->getNbpart() - 1);
                $reservation->addEvenement($evenement);
                $reservation->addClient($user);
                $this->addFlash('success', 'votre reservation a ete enregistré');

                $this->entityManager->persist($reservation);
                $this->entityManager->flush();

                $message = (new \Swift_Message('Reservation'))
                    ->setFrom('lambda.pi.dev@gmail.com')
                    ->setTo($user->getMail())
                    ->setBody(
                        $this->renderView(
                        // templates/emails/registration.html.twig
                            'emails/registration.html.twig',
                            ['name' => $evenement->getNom()
                            ]
                        ),
                        'text/html'
                    )


                ;

                $mailer->send($message);



            }
            else{
                $this->addFlash('error', 'Il n\'ya pas des places');

            }



        }
        return $this->redirectToRoute('event_index');





    }

    /**
     * @Route("/event/plus/Resrver", name="event_plus_reserver", methods={"GET"})
     */

    public function plusReserve(){

        $events = $this->evenementRepository->findEventPlusReserve();
        return $this->render('evenement/plusReserve.html.twig',[
            'events' => $events
        ]);
    }

    /**
     * @Route("/event/plus/Resrver/front", name="event_plus_reserver_front", methods={"GET"})
     */

    public function plusReserveFront(){

        $events = $this->evenementRepository->findEventPlusReserve();
        return $this->render('evenement/plusReserverFront.html.twig',[
            'events' => $events
        ]);
    }



}