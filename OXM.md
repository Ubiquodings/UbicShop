- RestTemplate

```java
URI url = new URI(url+serviceKey);
response = restTemplate.exchange(url, HttpMethod.GET, entity, CustomResponse.class);
```



### 샘플

``` java
//
<response>
    <a>값</a>
</reponse>

//
@Setter    
@Getter    
@XmlRootElement(name="response")
public class Response{
    private String a;
}    
    
```



``` java
//
<response>
	<item>
		<a>값</a>
	</item>
<response>


//
@Setter    
@Getter    
@XmlRootElement(name="response")
public class Response{
    private Item item;
    
    @Setter
    @Getter
    @XmlRootElement(name="item")
    public static class Item{
        private String a;
    }
}    
```



// 여기가 우리 데이터 형태과 가장 근접한 예시

``` java
//
<response>
	<items>
		<item>
			<a>값</a>
		</item>
...
		<item>
			<a>값</a>
		</item>
	<items>
<response>

//
@Setter
@XmlRootElement(name="response")
public class Response{
    private List<Item> items;
    
    @XmlElementWrapper(name="items") // 특정 노드들을 감싸고 있음
    @XmlElement(name="item")
    public List<Item> getItems(){
        return items;
    }
    
    @Setter
    @Getter
    @XmlRootElement(name="item")
    public static class Item{
        private String a;
    }
}    
```



