package com.roboprogs.np.script;

import java.util.*;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Base class to interact with a JavaScript script.
 */
public abstract class JSSupport {

    /** instance counter */
    private static int cnt = 0;
    
	/** data to identify instances */
	private final String data;

	/** init - MUST be a default constructor to work with JavaScript */
	protected JSSupport( ) {
        synchronized ( JSSupport.class ) {
            JSSupport.cnt++;
            this.data = "" + JSSupport.cnt;
        }
	}

	/** force superclass and subclass to interact */
	public void run() {
		jprint( "BEFORE helper routine");
		help();
		jprint( "AFTER helper routine");
	}

	/** "callback" for subclass to implement */
	protected abstract void help()
		;

	/** print stuff, Java style */
	protected void jprint( String s) {
		System.out.println( "[Instance " + this.data +  "] JAVALAND print: " + s);
	}

	/** print stuff, Java style, but with JavaScript input */
	protected void jprint( ScriptObjectMirror o) {
        Map <String, Object> props;

        props = new TreeMap <> ( o );
        jprint( props.toString() );
	}

	/** print stuff, Java style, but with JavaScript input */
	protected void jprint(
            String intro,
            ScriptObjectMirror o) {
        Map <String, Object> props;

        props = new TreeMap <> ( o );
        jprint( intro + ":" + props.toString() );
	}

}

// vi: ts=4 sw=4 ai expandtab
// *** EOF ***