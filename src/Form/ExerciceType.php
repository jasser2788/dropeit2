<?php

namespace App\Form;

use App\Entity\Exercice;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ExerciceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('name')
            ->add('level', ChoiceType::class, [
                'choices'  => [
                    'Easy' =>'Easy',
                    'Intermediate' =>'Intermediate' ,
                    'Hard' =>  'Hard',
                ],
            ])
            ->add('image' ,FileType::class,array('data_class' => null,'required' => false))
            ->add('round', ChoiceType::class, [
                'choices'  => [
                    'one round' =>true,
                    'two round' =>'2' ,
                    'three rounds' =>  '3',
                ],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Exercice::class,
        ]);
    }
}
