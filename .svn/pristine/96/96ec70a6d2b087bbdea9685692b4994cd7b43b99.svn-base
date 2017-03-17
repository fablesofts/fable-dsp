<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <style type="text/css">
            #clock {    
                stroke: #adcd3c;
                stroke-linecap: round;
                fill: #f2fddb;
            }
            #face {
                stroke-width: 3px;            
            }
            #ticks {
                stroke-width: 2px;
            }
            #hands line {                
                stroke-linejoin: bevel;                
            }
            #hourhand {
                stroke-width: 4px;                        
            }
            #minutehand {
                stroke-width: 3px;                
            }
            #numbers {
                font-size: 16px;
                text-anchor: middle;
                stroke: none;
                fill:  #92b0dd;
            }
            #leftDiv{
                width:49%;
                float:left;
                margin-left:10px;
            }
            #rightDiv{
                width:49%;
                float:right;
            }
        </style>
        <div id="leftDiv">
            <svg id="clock" viewBox="0 0 100 100" width="500" height="500">
            <defs>
            <!-- define an filter use to add shadow of some element -->
            <filter id="shadow" x="-50%" y="-50%" width="200%" height="200%">
                <feGaussianBlur in="SourceAlpha" stdDeviation="1" result="blur" />
                <feOffset in="blur" dx="-1" dy="1" result="shadow" />
                <feMerge>
                    <feMergeNode in="SourceGraphic"/>
                    <feMergeNode in="shadow" />
                </feMerge>
            </filter>
            </defs>
            <!-- clock face -->
            <circle id="face" cx="50" cy="50" r="45" />
            <!-- mark time lines -->
            <g id="ticks">
            <line x1="50.00" y1="5.000" x2="50.00" y2="10.00" />
            <line x1="72.50" y1="11.03" x2="70.00" y2="15.36" />
            <line x1="88.97" y1="27.50" x2="84.64" y2="30.00" />
            <line x1="95.00" y1="50.00" x2="90.00" y2="50.00" />
            <line x1="88.97" y1="72.50" x2="84.64" y2="70.00" />
            <line x1="72.50" y1="88.90" x2="70.00" y2="84.64" />
            <line x1="50.00" y1="95.00" x2="50.00" y2="90.00" />
            <line x1="27.50" y1="88.90" x2="30.00" y2="84.64" />
            <line x1="11.03" y1="72.50" x2="15.36" y2="70.00" />
            <line x1="5.000" y1="50.00" x2="10.00" y2="50.00" />
            <line x1="11.03" y1="27.50" x2="15.36" y2="30.00" />
            <line x1="27.50" y1="11.00" x2="30.00" y2="15.36" />
            </g>
            <!-- mark some important numbers -->
            <g id="numbers"> 
            <text x="50" y="20">12</text>
            <text x="85" y="55">3</text>
            <text x="50" y="88">6</text>
            <text x="15" y="55">9</text>            
            </g>
            <!-- show hands -->

            <line id="hourhand" x1="50" y1="50" x2="50" y2="24" />
            <line id="minutehand" x1="50" y1="50" x2="50" y2="20" />
            <line id="secondhand" x1="50" y1="50" x2="50" y2="16" />
            </svg>

        </div>
		<div id="rightDiv">
			<div class="easyui-calendar" style="width:250px;height:250px;"></div>
		</div>
        <script type="text/javascript">
            function updateTime() {
                var now = new Date();
                var second = now.getSeconds();
                var min = now.getMinutes();
                var hour = (now.getHours() % 12) + min / 60;
                var secondangle = second * 6; //6 degrees for every minute
                var minangle = min * 6;        //6 degrees for every minute
                var hourangle = hour * 30;    //30 degrees for every hour

                $('#minutehand').attr('transform', 'rotate(' + minangle + ', 50, 50)');
                $('#hourhand').attr('transform', 'rotate(' + hourangle + ', 50, 50)');
                $('#secondhand').attr('transform', 'rotate(' + secondangle + ', 50, 50)');
            }
            window.onload = function() {
                window.setInterval(updateTime, 1000);
            };
        </script>
    </body>
</html>