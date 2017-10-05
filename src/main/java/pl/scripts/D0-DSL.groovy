package pl.scripts

class Test {

def show = {println it}
def power = {it ** 2}
    def please(action) {
        [the: { what -> [of: (action(what(it)))] }]
    }

    void test () {
        show '123'
        show power(2)
        please show the power of 3
    }
}

new Test().test()

