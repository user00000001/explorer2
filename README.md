## Explorer project Installation and Deployment instructions


### Overview

This is the Explorer project for the Tesra blockchain. The project is based on front and back separation framework. The Explorer back-end-projects contains two projects, the TstSyncHandler project synchronizes block and transaction information to the local database and the Explorer project provides Restful API to query transaction,tstid,block information for the Explorer front-end and DApps. The Explorer front-end is based on Vue framework. 

### Installation Environment

- JDK Version: JDK 8 and above
- Database: Mysql5.6 and above
- Nginx: nginx1.11.3 and above
- Node: node v8.9.3 and above



### Build

#### Explorer back-end-projects

The two Explorer back-end projects compilation relies on **maven** tools. If the system is not configured with **maven** tools, you can download it on the maven website.

Download URL：

>[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi)

Firstly, cd the TstSyncHandler project **lib** directory, run the following command:

```
mvn install:install-file -DgroupId=com.github.TesraSupernet -DartifactId=javasdk -Dversion=v0.0.1 -Dpackaging=jar -Dfile=tesrasdk-java.jar
```

Then, cd the TstSyncHandler project root directory，run the following command:
```
mvn clean package
```

After the compilation is successful, you will generate a jar package **tstsynchandler-0.0.1-SNAPSHOT.jar**  in the project **target** directory to run. 


Then, cd the Explorer project root directory，run the following command:
```
mvn clean package
```

After the compilation is successful, you will generate a jar package **explorer-0.0.1-SNAPSHOT.jar**  in the project **target** directory to run. 


#### Explorer front-end project

The Explorer front-end Project compilation relies on **node** tools. If the system is not configured with **node** tools, you can download it on the node website.
Download URL:
>[https://nodejs.org/en/download/](https://nodejs.org/en/download/)

Firstly,open the Explorer front-end project **config/prod.env.js** file , change following attribute:
```
API_URL:Your API URL
```

Then, cd the Explorer front-end project root directory，run the following command:
```
npm install
```

```
npm run build
```

After all these step, you will generate **index.html** and **static** folder in Folder **dist** 


### Configuration

#### TstSyncHandler
The Mysql tables build script and Properties of the project in the **config** directory

#### mysql tables build script

- **tbl_tst_block** 
Storage block information.
- **tbl_tst_txn_detail**
Storage transaction information.
- **tbl_tst_tstid_detail**
Storage transaction information on the tstId operation.
- **tbl_tst_current**
Storage the current block and transaction amount.(Don't forget the insert statements in the script.)


#### application.properties

In addition to the default spring boot configuration, you only need to modify the following configuration items.

```
################port#######################
//service startup port
server.port=7575    

################database##################
//the type of database
spring.jpa.database = MYSQL
//the driver for database
spring.datasource.driverClassName = com.mysql.jdbc.Driver
//the url for database
spring.datasource.url = jdbc:mysql://localhost:3306/explorer?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
//the username for database
spring.datasource.username = root
//the password for databse
spring.datasource.password = 1111

################blockchain param##########
//the master Tesra node restful api url
node.restful.url = http://127.0.0.1:20555
//the amount of Tesra blockchain nodes
node.amount=4
//the node restful api url
node.restful.url_0=http://127.0.0.1:20555
node.restful.url_1=http://127.0.0.1:20556
node.restful.url_2=http://127.0.0.1:20557
node.restful.url_3=http://127.0.0.1:20558

//maximum fault tolerance times for each node
node.interruptTime.max = 30
//maximum fault tolerance times for each node
node.waitForBlockTime.max = 30

//the interval for block generate
block.interval=6000

//record smart contract codehash（hexadecimal format）
claimRecord.codeHash = 36bb5c053b6b839c8f6b923fe852f91239b9fccc
//tstId smart contract codehash（hexadecimal format）
tstId.codeHash = ff00000000000000000000000000000000000003
//TST asset smart contract codehash（hexadecimal format）
asset.tst.codeHash = 0100000000000000000000000000000000000000
//TSG asset smart contract codehash(hexadecimal format)
asset.tsg.codeHash = 0200000000000000000000000000000000000000
//authority smart contract codehash(hexadecimal format)
auth.codeHash = 0600000000000000000000000000000000000000

```



#### Explorer
The Mysql tables build script and Properties of the Explorer project in the **config** directory

#### mysql tables build script

- **tbl_tst_block** 
Storage block information.
- **tbl_tst_txn_detail**
Storage transaction information.
- **tbl_tst_tstid_detail**
Storage transaction information on the tstId operation.
- **tbl_tst_current**
Storage the current block and transaction amount.(Don't forget the insert statements in the script.)


#### application.properties

In addition to the default spring boot configuration, you only need to modify the following configuration items.

```
################port#######################
//service startup port
server.port=7575    

################database##################
//the type of database
spring.jpa.database = MYSQL
//the driver for database
spring.datasource.driverClassName = com.mysql.jdbc.Driver
//the url for database
spring.datasource.url = jdbc:mysql://localhost:3306/explorer?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
//the username for database
spring.datasource.username = root
//the password for databse
spring.datasource.password = 1111



masternode.restful.url = http://dapp1.tesra.me:25770

genesisblock.time=1530316800

```


#### Explorer front-end project

Put the  **index.html** and **static** folder into the correct directory according to the Nginx server configuration
Example, the Nginx server configuration is

```
....
   server {
        listen       80;
        server_name  localhost;
        location / {
            root   /root/www/;
            index  index.html index.htm;
        }
    }
....
```
So, put the  **index.html** and **static** folder into your system **/root/www** directory.


### Running

When the above steps are completed, you can run the Explorer project.


Attention:
>The Spring boot framework loads the application.properties configuration file from these paths at priority.
>- The /config directory in the current directory.
>- In the current directory
>- The /config directory in the classpath.
>- The classpath to the directory

So, you‘d better make sure the **explorer-0.0.1-SNAPSHOT.jar** and the /config directory in the same directory. Then run the following command:
```
java -jar explorer-0.0.1-SNAPSHOT.jar
```

To run the project in the background, run the following command:

```
nohup java -jar explorer-0.0.1-SNAPSHOT.jar
```


You‘d better make sure the **tstsynchandler-0.0.1-SNAPSHOT.jar** and the /config directory in the same directory. Then run the following command:
```
java -jar tstsynchandler-0.0.1-SNAPSHOT.jar
```

To run the project in the background, run the following command:

```
nohup java -jar tstsynchandler-0.0.1-SNAPSHOT.jar
```


### Verification

- check the process

In the linux system，use the following command ```ps -ef|grep java``` , then check  for the following process.
```
java -jar target/explorer-0.0.1-SNAPSHOT.jar
java -jar target/tstsynchandler-0.0.1-SNAPSHOT.jar
```


- check the log

cd the Explorer back-end project **log** directory，check for the latest log display and ERROR information.


- check by your browser
Input the ip and port into your browser according to your Nginx server configuration, you can see the block and transaction information in your browser just like the [Tesra Explore](https://www.tesra.me)



### License

The TesraSupernet library (i.e. all code outside of the cmd directory) is licensed under the GNU Lesser General Public License v3.0, also included in our repository in the License file.


