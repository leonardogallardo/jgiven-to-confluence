# JGiven to Confluence

This is a lib created to feed a confluence page with Jgiven scenarios automatically.

## Compatibility

Currently we only support gradle and jgiven results json should be located under `build/jgiven-results/test/`

## How to use

- Include dependency in your classpath
- Create a .properties file with name 'jgiven2confluence.properties' in you resources folder
- Add the following properties: url, username, password and pageNumber
- Execute JgivenToConfluence().send()

## Technologies used
- Kotlin
- Jackson (read jgiven json files)
- j2html (to generate html structure)
- htmlcompressor (to minify generated html)
- rest-assured (to send request to confluence)