# Gson vs Jackson vs Fastjson

```
 Time spent in nanoseconds, the smaller the better:

   Parser   toJson   toPojo    Total

 ----------10000 laps--------------
     GSON    91697    79922   171620
  JACKSON    24763    47092    71856
 FASTJSON    44225    16866    61092

 ----------100000 laps--------------
     GSON    33747    21821    55568
  JACKSON     2517     5323     7840
 FASTJSON     1789     3960     5749

 ```
