package pl.scripts

def data = '''
<users type="work">
    <user>
        <name>jkowalski</name>
        <email type="work">jan@wp.pl</email>
    </user>
    <user>
        <name>mnowak</name>
        <email type="work">marek@wp.pl</email>
    </user>
</users>
'''

def users = new XmlParser().parseText(data)


println users.user.email.text()
println users instanceof groovy.util.Node
println users.attribute("type")
List<Node> usersNodes = users.children()

println users.children()[1].attribute("type")

println usersNodes[0].children()[1].attribute("type")

