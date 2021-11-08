<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.*" %>

<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

<z:layout pageTitle="Tcase chart">

</z:layout>

<div class="chart-container" style="position: relative; height:20vh; width:40vw; margin: 0 auto; text-align: center;">
	<canvas id="myChart"></canvas>
</div>

<script>
function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

const frameworks = ${test_cases_json}.map((test_case) => {
	  return test_case.framework;
})

const frameworks_indexes = frameworks.reduce(function(acc, el) {
	  acc[el] = (acc[el] || 0) + 1;
	  return acc;
	}, {});


function getColors() {
	let colorsArray = [];

	for (let i = 0; i < [...new Set(frameworks)].length; i++) {
		colorsArray.push(getRandomColor())
	}
	return colorsArray;
}

const data = {
		  labels: Object.keys(frameworks_indexes),
		  datasets: [{
		    label: 'My First Dataset',
		    data: Object.values(frameworks_indexes),
		    backgroundColor: getColors(),
		    hoverOffset: 4
		  }]
		};
		
const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'pie',
    data: data,
});
</script>
