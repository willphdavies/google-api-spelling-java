package org.xeustechnologies.googleapi.spelling;

public class Test {
    @org.junit.Test
    public void check() {

        Configuration config = new Configuration();
        config.setProxy( "10.105.24.70", 8080, "http" );

        SpellChecker checker = new SpellChecker( config );
        checker.setOverHttps( true );
        checker.setLanguage( Language.ENGLISH );

        SpellRequest request = new SpellRequest();
        request.setText( "helloo helloo worlrd" );
        request.setIgnoreDuplicates( 1 );

        SpellResponse spellResponse = checker.check( request );

        for( SpellCorrection sc : spellResponse.getCorrections() )
            System.out.println( sc.getValue() );
    }
}
