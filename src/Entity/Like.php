<?php

namespace App\Entity;

use App\Repository\LikeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=LikeRepository::class)
 * @ORM\Table(name="`like`")
 */
class Like
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $id_client;

    /**
     * @ORM\ManyToOne(targetEntity=Poste::class, inversedBy="likes")
     * @ORM\JoinColumn(nullable=false)
     */
    private $id_poste;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdClient(): ?int
    {
        return $this->id_client;
    }

    public function setIdClient(?int $id_client): self
    {
        $this->id_client = $id_client;

        return $this;
    }

    public function getIdPoste(): ?Poste
    {
        return $this->id_poste;
    }

    public function setIdPoste(?Poste $id_poste): self
    {
        $this->id_poste = $id_poste;

        return $this;
    }
}
