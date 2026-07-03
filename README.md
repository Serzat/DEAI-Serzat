# OPT3 Casus Java – COA-plaatsingssysteem

Deze Java 17-applicatie demonstreert drie ontwerppatronen binnen een vereenvoudigd COA-plaatsingsproces:

- **Strategy Pattern** voor verwisselbare gemeenteselectie.
- **Template Method Pattern** voor een vaste verwerkingsvolgorde.
- **Observer Pattern** voor automatische dossiermeldingen.

## Projectstructuur

- `src/main/java` – productiecode.
- `src/test/java` – JUnit 4-regressietests.
- `docs` – rapportage en UML-bestanden.
- `lib` – lokale JUnit/Hamcrest-jars voor handmatig testen.
- `pom.xml` – Maven-configuratie voor Java 17.

## Bouwen en testen met Maven

```bash
mvn clean test
```

## Handmatig bouwen zonder Maven

Linux/macOS:

```bash
rm -rf out
mkdir -p out/main out/test
javac --release 17 -Xlint:all -Werror -d out/main $(find src/main/java -name "*.java")
javac --release 17 -Xlint:all -Werror \
  -cp "out/main:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
  -d out/test $(find src/test/java -name "*.java")
java -cp "out/main:out/test:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
  org.junit.runner.JUnitCore test.PlaatsingTest
```

Windows PowerShell gebruikt `;` in plaats van `:` in het classpath.

## Belangrijke ontwerpkeuze

`Vluchteling.plaatsInAZC(...)` is het enige publieke mutatiepunt voor een plaatsing. De interne mutatiemethoden van `AZC` en `Gemeente` zijn bewust package-private. Zo kunnen de vluchteling, de bewonerslijst en de gemeentelijke capaciteit niet los van elkaar worden aangepast.
