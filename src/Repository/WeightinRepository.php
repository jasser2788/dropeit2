<?php

namespace App\Repository;

use App\Entity\Weightin;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Weightin|null find($id, $lockMode = null, $lockVersion = null)
 * @method Weightin|null findOneBy(array $criteria, array $orderBy = null)
 * @method Weightin[]    findAll()
 * @method Weightin[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class WeightinRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Weightin::class);
    }

    // /**
    //  * @return Weightin[] Returns an array of Weightin objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('w')
            ->andWhere('w.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('w.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Weightin
    {
        return $this->createQueryBuilder('w')
            ->andWhere('w.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
