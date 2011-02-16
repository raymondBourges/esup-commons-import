<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<h1>esup-blank-springmvc-servlet</h1>

<hr/>
<h2 class="alt">
  ${user.username} !
</h2>

<hr/>

<div class="span-7 colborder">

  <h6><spring:message code="view.description.messageSpringMvc"/></h6>
  <p id="helloPart"><spring:message code="view.helloString" arguments="${user.username}"/></p>
  
</div>


<div class="span-8 colborder">
  
  <h6><spring:message code="view.description.messageJquery"/></h6>
  <span id="helloPartJQuery"></span>

</div>


<div class="span-7 last">
  
  <h6><spring:message code="view.description.messageSpringMvcJquery"/> <a href="ajax" id="urlAjax">url</a>)</h6>
  <span id="helloPartJQueryAjax"></span>
  
</div>

<hr/>

<div class="span-7 colborder">

  <h6><spring:message code="view.description.messageSpringMvcJqueryGetJson"/> <a href="ajaxUser" id="urlAjaxJsonGet">url</a>)</h6>
  <span id="helloPartJQueryAjaxGetJson"></span>
  
</div>


<div class="span-8 colborder">
  
  <h6><spring:message code="view.description.messageSpringMvcJqueryGetPutJson"/> <a href="ajaxUserPut" id="urlAjaxJsonGetPut">url</a>)</h6>
  <span id="helloPartJQueryAjaxGetPutJson"></span>
  
</div>

<hr/>
<hr/>

<div class="span-20 prepend-1 colborder">

  <form:form action="" method="get" commandName="user" id="user">
    <form:input path="username" id="username"/>
    <input id="submit" type="submit" value="submit" />
  </form:form>

  <script>
    $("#username").keyup(function () {

    	
    // simple jquery
    var value = $(this).val();

    $("#helloPartJQuery").text(value);


    // jquery ajax  ; put : text message ; get : text message 
    $.get('ajax', {username: value}, function(data) {
    $('#helloPartJQueryAjax').html(data);
    });

    var urlAjax = "ajax?username="+value;
    $("#urlAjax").attr("href", urlAjax);


    // jquery ajax  ; put : text message ; get : json user message 
    $.getJSON("ajaxUser?username="+value, function(data) {
    	 $('#helloPartJQueryAjaxGetJson').html(data.username);
    });
    
    var urlAjaxJsonGet = "ajaxUser?username="+value;
    $("#urlAjaxJsonGet").attr("href", urlAjaxJsonGet);


    // jquery ajax  ; put : json user message ; get : json user message 
    $.post('ajaxUserPut', {"username":value}, function(data) {
    	 $('#helloPartJQueryAjaxGetPutJson').html(data.username);
    });
    
    var urlAjaxJsonGetPut = "ajaxUser";
    $("#urlAjaxJsonGetPut").attr("href", urlAjaxJsonGetPut);
    
    
    }).keyup();
  </script>

</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
