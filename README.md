# ALFrame
Permit converting a raw frame received through a network, into a Java Object.

### Example 1: 
`String frame="first%78%12547 Kg%04-20-17%16:13:19%211 int"`

The received frame is composed of differents tockens representing information, each tocken is delimited from the other one by a delimiter.
In the example 1: 
- "%" represente the delimiter
- tockens are:  "first", "78", "12547", "04-20-17", "16:13:19", "211 int"
- tockens can have various of type: Integer, Double, String, date, time, BitSet 

Here are the four steps to follow, in order to convert any received frame into Java object
1. Create class that extends the abstract class `ALFrame` 
`class CustromFrame extends ALFrame`
2.Override the abstract method:

`protected void parseFrame(String[] tockensArray)`

Where the parameter `tockensArray` represente all the frame tockens, indexed in the same order that they appear in the frame

3. Inside the `parseFrame` method, we match each tocken with his ALFrame API associated class:
- `StringFrameTocken` for a `String`.
- `IntegerFrameTocken` for an `Integer`.
- `DoubleFrameTocken` for a `Double`.
- `DateFrameTocken` for a `Date`.
- `TimeFrameTocken` for a `Time`.
- `BitSetFrameTocken` for a `BitSet` (Integer number which is converted to binary base, and where each digit represente a boolean state).

4. associate the tocken with a key through the method:
`addTocken(FrameTocken frame,String key)` 

### Example
```
private static class CustomFrame extends ALFrame {
        public CustomFrame(String frame, String delimiter) {
            super(frame, delimiter);
        }

        @Override
        protected void parseFrame(String[] tockensArray) {

            StringFrameTocken stringFrameTocken = new StringFrameTocken(tockensArray[0]);
            //the tocken in index 0 is a string, we associate this tocken with a key: "order"
            this.addTocken(stringFrameTocken, "order"); 

            

            DoubleFrameTocken doubleFrameTocken = new DoubleFrameTocken(tockensArray[2]);
            this.addTocken(doubleFrameTocken, "weigh");

            DateFrameTocken dateFrameTocken = new DateFrameTocken(tockensArray[3], DateFrameTocken.DatePattern.MDY, "-");
            this.addTocken(dateFrameTocken, "date");
            
            TimeFrameTocken timeFrameTocken=new TimeFrameTocken(tockensArray[4], TimeFrameTocken.TimePattern.HMS);
            this.addTocken(timeFrameTocken, "time");

            IntegerFrameTocken integerFrameTocken=new IntegerFrameTocken(tockensArray[5]);
            this.addTocken(integerFrameTocken, "counter");
            
            BitSetFrameTocken bitSetFrameTocken = new BitSetFrameTocken(tockensArray[1], BitSetFrameTocken.Type.HEXA);
            bitSetFrameTocken.putbitSetKey("state1", 0);
            bitSetFrameTocken.putbitSetKey("state2", 2);
            this.addTocken(bitSetFrameTocken, "infoState");
        }
    }
```

When executing:

``` 
public static void main(String[] args) {
    String frame = "first%78%12547 Kg%04-20-17%16:13:19%211 int";

        CustomFrame customFrame = new CustomFrame(frame, "%");

        System.out.println(customFrame.getStringFromTocken("order"));
        System.out.println(customFrame.getBitSet("infoState"));        
        System.out.println(customFrame.getDoubleFromTocken("weigh"));
        System.out.println(customFrame.getDateFromTocken("date").toString("dd/MM/YYYY"));
        System.out.println(customFrame.getTimeFromTocken("time").toString("HH:mm:ss"));
        System.out.println(customFrame.getIntegerFromTocken("counter"));

    }
```

The output will be: 
`first
{1, 2, 3, 4}
true
12547.0
20/04/2017
16:13:19
211
`


