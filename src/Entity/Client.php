<?php

namespace App\Entity;

use App\Repository\ClientRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ClientRepository::class)
 */
class Client
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $mail;

    /**
     * @ORM\ManyToMany(targetEntity=Reservation::class, inversedBy="clients")
     */
    private $reservetion;

    public function __construct()
    {
        $this->reservetion = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(string $mail): self
    {
        $this->mail = $mail;

        return $this;
    }

    /**
     * @return Collection|Reservation[]
     */
    public function getReservetion(): Collection
    {
        return $this->reservetion;
    }

    public function addReservetion(Reservation $reservetion): self
    {
        if (!$this->reservetion->contains($reservetion)) {
            $this->reservetion[] = $reservetion;
        }

        return $this;
    }

    public function removeReservetion(Reservation $reservetion): self
    {
        $this->reservetion->removeElement($reservetion);

        return $this;
    }

    public function __toString()
    {
        return (string)$this->getId();


    }

}
