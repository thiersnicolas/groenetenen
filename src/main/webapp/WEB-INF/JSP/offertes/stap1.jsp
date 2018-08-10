<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Aanvraag offerte (stap 1)' />
</head>
<body>
	<v:menu />
	<h1>Aanvraag offerte</h1>
	<h2>Stap 1</h2>
	<form:form action='${url}' modelAttribute='offerte'>
		<form:label path='voornaam'>Voornaam:<form:errors
				path='voornaam' />
		</form:label>
		<form:input path='voornaam' autofocus='true' required='required' />
		
		<form:label path='familienaam'>Familienaam:
			<form:errors path='familienaam' />
		</form:label>
		<form:input path='familienaam' required='required' />
		
		<form:label path='emailAdres'>E-mail adres:
			<form:errors path='emailAdres' />
		</form:label>
		<form:input path='emailAdres' required='required' type='email' />
		
		<div>Telefoonnummer(s):</div>
		<c:forEach items="${offerte.telefoonNrs}" varStatus="status">
			<div class="rij"><form:input path="telefoonNrs[$(status.index}]" tpe="tel"/>
			<form:errors path="telefoonNrs[$status.index}]" cssClass="fout"/></div>
		</c:forEach>
		<input type="submit" name="nogeennummer" value="Nog een telefoonnummer" formnovalidate/>
		
		<input type='submit' value='Volgende stap' name='volgende'>
	</form:form>
</body>
</html>