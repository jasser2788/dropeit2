<?php

namespace App\Form;

use App\Entity\Programme;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProgrammeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('name')
            ->add('type', ChoiceType::class, [
                'choices'  => [
                    'Full Body' =>'Full Body',
                    'Weight Loss' =>'Weight Loss' ,
                    'Resistance' =>  'Resistance',
                ],
            ])
            ->add('image',FileType::class,array('data_class' => null,'required' => false))
            ->add('days')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Programme::class,
        ]);
    }
}
