# Introduction #

This is a simple Java API that makes it very easy to call Google's spell checker service from Java applications.


# How to use it? #

Below is a simple example that demonstrate it's usage.

```
    SpellChecker checker = new SpellChecker();

    SpellResponse spellResponse = checker.check( "helloo worlrd" );

    for( SpellCorrection sc : spellResponse.getCorrections() )
        System.out.println( sc.getValue() );
```

This will print all the corrections available for the text "helloo worlrd".

It is also possible to set more options to the request, here is a more meatier example.

```
    // Proxy settings
    Configuration config = new Configuration();
    config.setProxy( "my_proxy_host", 8080, "http" );

    SpellChecker checker = new SpellChecker( config );
    checker.setOverHttps( true ); // Use https
    checker.setLanguage( Language.ENGLISH ); // Use English (default)

    SpellRequest request = new SpellRequest();
    request.setText( "helloo helloo worlrd" );
    request.setIgnoreDuplicates( 1 ); //Ignore duplicates

    SpellResponse spellResponse = checker.check( request );

    for( SpellCorrection sc : spellResponse.getCorrections() )
        System.out.println( sc.getValue() );
```


## What happens in the background? ##

The **SpellChecker** class transforms the request into XML and sends it to the Google's spell checker service. The response is also in XML, which is then deserialized into simple POJOs.

The request to the first example above looks like:

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<spellrequest textalreadyclipped="0" ignoredigits="1" ignoreallcaps="1" ignoredups="0">
    <text>helloo worlrd</text>
</spellrequest>
```

And the response XML looks like:
```
<?xml version="1.0" encoding="UTF-8"?>
<spellresult error="0" clipped="0" charschecked="13">
    <c o="0" l="6" s="1">hello	Helli	hell	hallo	hullo</c>
    <c o="7" l="6" s="1">world	whorled	wold	warlord	would</c>
</spellresult>
```

The API uses **JAXB** for Java/XML transformation.