@Grapes([
    @Grab(group='com.h2database', module='h2', version='1.3.176'),
    @GrabConfig(systemClassLoader = true)
])
import groovy.sql.Sql

def db = [url:'jdbc:h2:mem', user:'sa', password:'', driver:'org.h2.Driver']
def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

sql.execute('''
	create table A(
		id integer not null primary key
		)
''')

sql.execute('''
	create table B(
		id integer not null primary key,
		value varchar(20)
		)
''')

def aTbl = sql.dataSet("A")
aTbl.add(id: 1)
aTbl.add(id: 2)
aTbl.add(id: 3)

def bTbl = sql.dataSet("B")
bTbl.add(id:1, value: "1")
bTbl.add(id:3, value: "3")

sql.eachRow("Select a.id, b.value from a left outer join b on a.id = b.id") {
	println(it.id + '|' + it.value)
}