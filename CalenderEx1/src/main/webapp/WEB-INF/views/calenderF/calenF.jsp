<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang='en'>
  <head>
    <meta charset='utf-8' />
	
	<link href="/test/resources/fullcalender/main.css" rel="stylesheet">
    <script src='/test/resources/fullcalender/main.js'></script>
<!--     <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script> -->

    <script>
      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth',
          editable : true, // 드래그해서 수정 가능한지, 길게 확장도 가능
          dayMaxEvents: 4, // +more 표시 전 최대 이벤트 개수, true는 쉡 높이에 의해 결정
	      locale: 'ko', // 한글 설정 추가
	      
          dateClick: function(info) { // 일자 클릭시 발생하는 이벤트
     //  	alert('Date: ' + info.dateStr);
	//     	calendar.addEvent( {'title':'evt4', 'start':'2019-09-04', 'end':'2019-09-06'});
	       
			var userInput = prompt('일정의 내용을 입력하세요.');
			
			if(!userInput){
				return;
			}
			
			calendar.addEvent( {  title: userInput, // 이벤트 제목
						          start: info.dateStr, // 이벤트 시작 날짜
						          end: info.dateStr, // 이벤트 종료 날짜
						      //  id: 'unique-id', // 이벤트의 고유 ID
						          allDay: true, // 종일 이벤트 여부
						          color: '#d4ff94', // 이벤트의 배경색 (대체 속성으로 backgroundColor 사용 가능)
						          textColor: '#1c1c1b' // 이벤트의 텍스트 색
	       						});
			
			let url = "calendar_insert.do";
			let param = "date_content="+userInput+"&start_day="+info.dateStr
						+"&end_day="+info.dateStr+"&id=${param.id}"
						+"&team_name=${param.team_name}";
			
			sendRequest(url, param, resultFn, "get");
/* 	       	var arrCal = calendar.getEvents();   
	       	alert("sad : "+arrCal[5].title);
	       	*/
	        },
	        
	        // 드래그로 인한 날짜 변화 수정
	        eventDrop: function(info) {
	            var event = info.event;
				
				let url = "calendar_day_update.do";
				let param = "start_day="+event.startStr
							+"&end_day="+event.endStr+"&date_idx="+event.id;
		/*
				let param = "start_day="+event.start.toISOString()
							+"&end_day="+event.end.toISOString()+"&date_idx="+event.id;
		*/		
				sendRequest(url, param, day_updFn, "get");
	            // 여기서 변경된 이벤트의 시작(start)과 끝(end) 정보를 확인하고 처리할 수 있습니다.
	        },
	        
	        // 일정 길이 변화 수정 >> 해결 안됨
	        eventResize: function(event, delta, revertFunc) {
	            // 리사이즈된 이벤트의 정보 출력
	            console.log('이벤트가 리사이즈되었습니다.');
	            console.log('리사이즈된 이벤트:', event);
	            console.log('리사이즈된 이벤트:', event._instance.startStr);
	            console.log('리사이즈된 이벤트:', event._instance.endStr);
	            console.log('변화량:', event._instance.endDelta);
	        	
	        	let url = "calendar_day_update.do";
	            let param = "start_day=" + event._instance.startStr +
	                        "&end_day=" + event._instance.endStr +
	                        "&date_idx=" + event._instance.id;

	            sendRequest(url, param, day_updFn, "get");
	        }
        });
        calendar.render();

	      //DB에서 가져온 데이터 리스트 
	    var eventsData = [
	    	<c:forEach var="vo" items="${list}"> 
	    		{ title: '${vo.date_content}', // 이벤트 제목
		          start: '${vo.start_day}', // 이벤트 시작 날짜
		          end: '${vo.end_day}', // 이벤트 종료 날짜
		          allDay: true, // 종일 이벤트 여부
		          color: '#d4ff94', // 이벤트의 배경색 (대체 속성으로 backgroundColor 사용 가능)
		          textColor: '#1c1c1b', // 이벤트의 텍스트 색
				  id: '${vo.date_idx}' // 이벤트의 고유 ID
				}
	    		 <c:if test="${!loop.last}">,</c:if> // 마지막 항목이 아닐 경우 쉼표 추가 > 
	    	</c:forEach> 
	    ];
	      
	    // 일정 데이터를 반복하여 calendar.addEvent() 호출       
	    eventsData.forEach(function(eventData) {
        calendar.addEvent(eventData);
     	});
	      
      });
      
		function resultFn() {
			
			console.log(xhr.readyState + " / " + xhr.status);
			
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				alert(data);
				if( data == 'no' ){
					alert("입력실패");
					return;
				}else{
					alert("입력성공");
					location.href="list.do";
				}
			}
		}

		
		function day_updFn() {
			console.log(xhr.readyState + " / " + xhr.status);

			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				alert(data);
				
				if( data == 'no' ){
					alert("수정실패");
					return;
				}else{
					alert("수정성공");
					location.href="list.do";
				}
			}
			
		}
      
      
    </script>
    
    <!-- Ajax 사용을 위한 httpRequest.js 처리 -->
	<script src="/test/resources/js/httpRequest.js"></script>
  </head>
  
  <body>
    <div id='calendar'></div>
    
    	<table border="1">
	    <c:forEach var="vo" items="${list}">
    		<tr>
				<td>${vo.date_idx}</td>    	
				<td>${vo.start_day}</td>    	
				<td>${vo.end_day}</td>    	
				<td>${vo.date_content}</td>    	
				<td>${vo.id}</td>    	
				<td>${vo.team_name}</td>    	
    		</tr>
    	</c:forEach>
    	</table>
    
    
    
    <!-- 일정 설정 창 -->
<!--     <form action="calender_insert.do">
    	<table>
			<tr>
				<th></th>
				<td><input name=""> </td>
			</tr>    	
    	
    	</table>
    </form> -->
    
  </body>
</html>