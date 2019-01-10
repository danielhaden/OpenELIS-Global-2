<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
					us.mn.state.health.lims.datasubmission.valueholder.DataIndicator,
					us.mn.state.health.lims.common.util.DateUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%!
    String basePath = "";
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
%>

<script type="text/javascript">
function saveAndSubmit() {
	if (checkURL()) {
		if (confirmSentWarning()) {
			showsubmitting();
			var form = document.forms[0];
			form.action = "DataSubmissionSave.do?submit=true";
			form.submit();
		}
	} else {
		alert("<spring:message code="datasubmission.warning.missingurl" />");
	}
}

function saveAndExit() {
	var form = document.forms[0];
	form.action = "DataSubmissionSave.do";
	form.submit();
}

function dateChange() {
	var month = $jq("#month").val();
	var year = $jq("#year").val();
	window.location.replace("DataSubmission.do?month=" + month + "&year=" + year);
}

function editUrl() {
	$jq("#url").removeAttr("disabled");
}

function checkURL() {
	return $jq("#url").val() != "";
}

function showsubmitting() {
	if( typeof(showSuccessMessage) != 'undefined' ){
	   	showSuccessMessage( false );
	}
   $jq("#sending").show();
	window.location = '#sending';
}

function confirmSentWarning() {
	var sentIndicators = [];
	var message = "<spring:message code="datasubmission.warning.sent" arguments="month, year"/>";
	$jq("span.<%=DataIndicator.SENT%>").each(function() {
		sentIndicators.push(this.id);
	});
	$jq("span.<%=DataIndicator.RECEIVED%>").each(function() {
		sentIndicators.push(this.id);
	});
	for (var i = 0; i < sentIndicators.length; i++) {
		message += "\n\u2022" + sentIndicators[i];
	}
	if (sentIndicators.length == 0) {
		return true;
	}
	return confirm(message);
}
<%if( request.getAttribute(IActionConstants.FWD_SUCCESS) != null && ((Boolean)request.getAttribute(IActionConstants.FWD_SUCCESS)) == true ) { %>
	if( typeof(showSuccessMessage) != 'undefined' ){
	   	showSuccessMessage( true );
	}
<% } %>
</script>

<div id="sending" style="text-align:center;color:DarkOrange;width:100%;font-size:170%;display:none;">
				<spring:message code="datasubmission.warning.sending"/>
</div>
<spring:message code="datasubmission.label.url" />: 
<form:input path="dataSubUrl.value" id="url" disabled="true"/>
<button type="button" onclick="editUrl();"><spring:message code="datasubmission.button.edit" /></button>

<h3><spring:message code="datasubmission.siteid"/> - <c:out value="${form.siteId}"/></h3>

<spring:message code="datasubmission.description" />

<table style="width:100%;border-spacing:0 5px;" >
<tr>
	<td><spring:message code="datasubmission.label.month" /></td>
	<td>
		<form:select path="month" id="month">
			<form:option value="1"><spring:message code="month.january.abbrev" /></form:option>
			<form:option value="2"><spring:message code="month.february.abbrev" /></form:option>
			<form:option value="3"><spring:message code="month.march.abbrev" /></form:option>
			<form:option value="4"><spring:message code="month.april.abbrev" /></form:option>
			<form:option value="5"><spring:message code="month.may.abbrev" /></form:option>
			<form:option value="6"><spring:message code="month.june.abbrev" /></form:option>
			<form:option value="7"><spring:message code="month.july.abbrev" /></form:option>
			<form:option value="8"><spring:message code="month.august.abbrev" /></form:option>
			<form:option value="9"><spring:message code="month.september.abbrev" /></form:option>
			<form:option value="10"><spring:message code="month.october.abbrev" /></form:option>
			<form:option value="11"><spring:message code="month.november.abbrev" /></form:option>
			<form:option value="12"><spring:message code="month.december.abbrev" /></form:option>
		</form:select>
	</td>
	<td><spring:message code="datasubmission.label.year" /></td>
	<td>
		<form:select path="year" id="year">
			<form:option value="2017">2017</form:option>
			<form:option value="2018">2018</form:option>
			<form:option value="2019">2019</form:option>
			<form:option value="2020">2020</form:option>
		</form:select>
	</td>
	<td><button type="button" onClick="dateChange();">Fetch Date</button></td>
</tr>
<tr>
	<td><b><spring:message code="datasubmission.checkbox.sendindicator"/></b></td>
	<td colspan="3"><b></b></td>
	<td><b></b></td>
</tr>
<c:forEach var="indicator" items="${form.indicators}">
	<c:set var="nameKey" value="${indicator.typeOfIndicator.nameKey}"/>
	<c:set var="descriptionKey" value="${indicator.typeOfIndicator.descriptionKey}"/>
<tr class="border_top">
	<td>
		<!--  checkbox-hidden combo trick to make struts send true when checked, false when unchecked as opposed to default checkbox (true when checked, false when unchecked)-->
		<form:checkbox path="indicator.sendIndicator" value="true" />
		<form:hidden path="indicator.sendIndicator" value="false" />
	</td>
	<td colspan="3">
		<span id="<spring:message code="${nameKey} }>" />" class="<c:out value="${indicator.status}" />">
			<b><spring:message code="${nameKey}" /></b>
		</span>
	</td>
	<td >
		<b><spring:message code="${nameKey}" /></b>
	</td>
</tr>
	<c:if test="${empty indicator.dataValue}" ><c:if test="${indicator.dataValue.visible}">
<tr>
	<td></td>
	<td  colspan="3"><c:out value="${indicator.dataValue.value}" /></td>
	<td >	<form:input path="indicator.dataValue.value"/> </td>
</tr>

	</c:if></c:if>
<tr>
	<td></td>
	<td  colspan="3">
		<spring:message code="${descriptionKey}" />
	</td>
</tr>
	<c:forEach var="resource" items="${form.indicators.resources}">
		<c:if test="${not empty resource.headerKey}">
<tr>
	<td></td>
	<td  colspan="3"></td>
	<td >
		<spring:message code="${resource.headerKey }" />
	</td>
</tr>
		</c:if>
		<c:forEach var="columnValue" items="${resource.columnValue}" varStatus="loop">
			<c:if test="${columnValue}">
<tr>
	<td></td>
	<td  colspan="3">
		
	</td>
	<td >
		<form:input path='indicator.resource.value[${loop.index}]"' />
			<c:if test="${not empty columnValue.displayKey}">
		<spring:message code="${columnValue.displayKey}>" />
			</c:if>
	</td>
</tr>

		</c:if>
	</c:forEach>
	</c:forEach>
<tr class="spacerRow"><td>&nbsp;</td></tr>
</c:forEach>
</table>

<button type="button" onclick="saveAndSubmit();"><spring:message code="datasubmission.button.savesubmit" /> </button>
<button type="button" onclick="saveAndExit();"><spring:message code="datasubmission.button.saveexit" /> </button>
