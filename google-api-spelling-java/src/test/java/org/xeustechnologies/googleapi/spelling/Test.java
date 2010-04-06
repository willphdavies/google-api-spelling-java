package org.xeustechnologies.googleapi.spelling;

public class Test {
    @org.junit.Test
    public void check() {
        SpellChecker checker = new SpellChecker();
        checker.setOverHttps( true ); // Now default is true
        checker.setLanguage( Language.ENGLISH ); // Default is English

        SpellRequest request = new SpellRequest();
        request.setText( "helloo helloo worlrd" );
        request.setIgnoreDuplicates( true );

        SpellResponse spellResponse = checker.check( request );

        for( SpellCorrection sc : spellResponse.getCorrections() )
            System.out.println( sc.getValue() );
    }
}
