# OldFashionedPound

OldFashionedPound is a Java lifrary implementing the 4 arithmetic operations (sum, subtraction,
multiplication and division) for pre-1970 UK prices. Under the old money system of UK, before 1970, there were 12 pence in a shilling and 20 shillings, or 240 pence, in a pound. Thus, a price in the OldUK Money system was expressed in Pounds, Shillings and Pence. For example &quot;12p 6s 10d&quot; is 12 Pounds, 6 Shillings and 10 Pence.

## Installation

Clone the project, execute mvn package, use jar file in target folder. 

```bash
git clone git@github.com:nikolagereci/old-fashioned-pound.git
mvn package
cd target
```

## Usage

```bash
java -jar "OldFashionedPound-0.0.1-SNAPSHOT.jar" "5p 17s 8d + 3p 4s 10d"
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.