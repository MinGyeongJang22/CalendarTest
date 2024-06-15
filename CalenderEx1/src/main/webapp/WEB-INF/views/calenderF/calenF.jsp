<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
       	  alert('Date: ' + info.dateStr);
          
	//     	calendar.addEvent( {'title':'evt4', 'start':'2019-09-04', 'end':'2019-09-06'});
	       	calendar.addEvent( {  title: 'test01', // 이벤트 제목
						          start: info.dateStr, // 이벤트 시작 날짜
						          end: info.dateStr, // 이벤트 종료 날짜
						          id: 'unique-id', // 이벤트의 고유 ID
						          allDay: true, // 종일 이벤트 여부
						          color: '#d4ff94', // 이벤트의 배경색 (대체 속성으로 backgroundColor 사용 가능)
						          textColor: '#1c1c1b' // 이벤트의 텍스트 색
	       						});
	       	var arrCal = calendar.getEvents();   
	       	alert("sad : "+arrCal[0].title);
	        }
        
        });
        calendar.render();
      });
      
      
    </script>
  </head>
  
  <body>
    <div id='calendar'></div>
  </body>
</html>