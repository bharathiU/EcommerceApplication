# EcommereceProvider

| Description    | Value |
| -------------- | ----- |
| Date Generated | Fri Jul 10 20:10:33 IST 2020 |
| Pact Version   | 3.5.11 |

## Verifying a pact between _consumerpact4_ and _EcommereceProvider_

Given **HP**  
get product list by HP  
&nbsp;&nbsp;returns a response which  
&nbsp;&nbsp;&nbsp;&nbsp;has status code **200** (<span style='color:green'>OK</span>)  
&nbsp;&nbsp;&nbsp;&nbsp;has a matching body (<span style='color:red'>FAILED</span>)  

| Path | Failure |
| ---- | ------- |
|$.manufacturerVOs.0.manufacturerName|Expected 'HP' but received 'Hp'|

Diff:

```diff
            "manufacturerId": "5679",
-            "manufacturerName": "HP",
+            "manufacturerName": "Hp",
            "productsList": [
```

