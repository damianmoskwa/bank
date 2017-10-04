package pl.training.groovy.bank.util

interface Observer<E> {

    void onEvent (E event)

}