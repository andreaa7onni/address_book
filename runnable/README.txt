Istruzioni per utilizzare il programma:

1) Nella presente directory configurare il file credenziali_database.properties sostituendo le credenziali del proprio sistema MySQL

2) dopo essersi connessi al proprio server MySQL, lanciare lo script di creazione del database (comando: >> source schema_database.sql) 

3) Eseguire Rubrica.jar

4) In quanto il progetto implementa un sistema di login iniziale con contatti della rubrica associati ad ogni utente, sarà necessario:
	-registrarsi con username e password
	-effettuare il login
	-aggiungere persone alla rubrica per poter effettuare test


NB. Per la compatibilità, nei test da me effettuati, ho utilizzato il server "10.4.32-MariaDB" (precedentemente in mio possesso) oltre alla libreria di connessione "mysql-connector-j-8.4.0.jar" (inclusa nel .jar consegnato).
questa libreria dovrebbe risultare compatibile con versioni precedenti di MySQL server.