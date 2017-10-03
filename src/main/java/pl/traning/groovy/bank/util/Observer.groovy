package pl.traning.groovy.bank.util

interface Observer<E> {

    void onEvent (E event)

}