package org.philzen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

// Inspection "Class 'IDEA_352970' may use Lombok @Setter"
// reported here ↓
public class IDEA_352970 {  // when applied, code can no longer compile

    private String listOfThings = "Foo;Bar";

    @SuppressWarnings("unused")
    public void someRegularMethod() {
        setListOfThings("Baz;Foo;Bar");
        assert "Baz;Foo;Bar".equals(listOfThings);
        
        setListOfThings(List.of(new Thing("FooThing"), new Thing("BarThing")));
        assert "FooThing;BarThing".equals(listOfThings);
    }
    
    public void setListOfThings(String thingList) { 
        this.listOfThings = thingList; 
    }

    public void setListOfThings(List<Thing> things) { 
        this.listOfThings = things.stream().map(Thing::getName).collect(Collectors.joining(";")); 
    }

    @Getter @AllArgsConstructor
    public static class Thing {
        private String name;
    }
}
