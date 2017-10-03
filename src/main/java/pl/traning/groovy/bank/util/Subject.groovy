package pl.traning.groovy.bank.util

trait Subject<E> {
    Set<Observer> observers = []

    void addObserver(Observer observer){
        observers << observer
    }

    void removeObserver(Observer observer){
        observers.remove(observer)
    }

    void  notifyObserver(E event){
        observers.each {it.onEvent(event)}
    }
}
