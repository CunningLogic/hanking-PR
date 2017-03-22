
function DyanmicChart(graphDiv, mouseEventDisabled){
	
	this.graphDiv = graphDiv;
	
	this.params = {};

	this.g = null;

	this.labels = null;
	this.datas = null;

	this.defaultLabel = ['X', 'A'];
	this.defaultDatas = [[0, 0], [1, 0]];
	
	this.mouseEventDisabled = mouseEventDisabled;
	
	this.eventListeners = {
			'mousedown' : DygraphEventHandler.onMouseDown,
            'mousemove' : DygraphEventHandler.onMouseMove,
            'mouseup' : DygraphEventHandler.onMouseUp,
            'click' : DygraphEventHandler.onClick,
            'dblclick' : DygraphEventHandler.onDoubleClick,
            'mousewheel' : DygraphEventHandler.onScroll
		};

	/**
	 * 初始化
	 */
	this.init = function(){

		this.params = {
			fractions: false,
			errorBars : false, 
			drawPoints : true,
			rollPeriod: 1,
			showRoller: true,
			connectSeparatedPoints: true,
			fillGraph: false,//填充
			animatedZooms: true,
			// valueRange: [50,125],
			  
			labelsDivWidth: 150,//标签宽度
			labelsSeparateLines: true,//竖排显示
			showLabelsOnHighlight: true,
			labelsDivStyles: { 'fontWeight': 'bold', 'background-color':'black', 'left':'0'},
//			xlabel: '',
//			ylabel: '',
			title : "",
//			labels : "",
//			legend : 'always',
			animatedZooms: true,
			colors : ['red', 'green', 'blue'],
			labels: this.defaultLabel,
			file : this.defaultDatas,
			interactionModel: this.eventListeners,
			clickCallback : function(e, x, points){//A function to call when the canvas is clicked.
				/**
				 * Type: function(e, x, points)
					e: The event object for the click
					x: The x value that was clicked (for dates, this is milliseconds since epoch)
					points: The closest points along that date. See Point properties for details.
					Default: null
				 */
			},
			drawCallback : function(dygraph, is_initial){//When set, this callback gets called every time the dygraph is drawn. This includes the initial draw, after zooming and repeatedly while panning.
				/**
					Type: function(dygraph, is_initial)
					dygraph: The graph being drawn
					is_initial: True if this is the initial draw, false for subsequent draws.
					Default: null
				 */
			},
			highlightCallback : function(event, x, points, row, seriesName){//When set, this callback gets called every time a new point is highlighted.
				/**
				 * Type: function(event, x, points, row, seriesName)
					event: the JavaScript mousemove event
					x: the x-coordinate of the highlighted points
					points: an array of highlighted points: [ {name: 'series', yval: y-value}, … ]
					row: integer index of the highlighted row in the data table, starting from 0
					seriesName: name of the highlighted series, only present if highlightSeriesOpts is set.
				 */
			},
			pointClickCallback : function(e, point){//A function to call when a data point is clicked. and the point that was clicked.
				/**
				 * Type: function(e, point)
					e: the event object for the click
					point: the point that was clicked See Point properties for details
				 */
			},
			underlayCallback : function(context, area, dygraph){//When set, this callback gets called before the chart is drawn. It details on how to use this.
				/**
				 * Type: function(context, area, dygraph)
					context: the canvas drawing context on which to draw
					area: An object with {x,y,w,h} properties describing the drawing area.
					dygraph: the reference graph
				 */
			},
			unhighlightCallback : function(event){//When set, this callback gets called every time the user stops highlighting any point by mousing out of the graph.
				/**
				 * Type: function(event)
					event: the mouse event
				 */
			},
			zoomCallback : function(minDate, maxDate, yRanges){//A function to call when the zoom window is changed (either by zooming in or out).
				/**
				 * Type: function(minDate, maxDate, yRanges)
					minDate: milliseconds since epoch
					maxDate: milliseconds since epoch.
					yRanges: is an array of [bottom, top] pairs, one for each y-axis.
				 */
			}
		};

		/*if(isNotEmpty(p) && !$.isEmptyObject(p)){
			$.each(p, function(k, v){
				this.params[k] = v;// 这样不行, 唉, 这个 this 不能指向 DyanmicChart 对象
			});
		}*/

		if(this.mouseEventDisabled){
			this.params["interactionModel"] = {
				'mousedown' : function(){},
	            'mousemove' : function(){},
	            'mouseup' : function(){},
	            'click' : function(){},
	            'dblclick' : function(){},
	            'mousewheel' : function(){}
			};
		}
		
	};
	this.init();
	
	/**
	 * 渲染
	 */
	this.render = function(datas, reset){

		// this.labels = isEmpty(labels) ? this.defaultLabel : labels;

		this.datas = isEmpty(datas) ? this.defaultDatas : datas;

		this.params["file"] = this.datas;
		this.params["labels"] = this.labels;
		
		if(isEmpty(this.g) || reset){

			this.g = new Dygraph(
				this.graphDiv,
				this.datas, 
				this.params
			);
		} else {
			this.g.updateOptions({ 'labels': this.labels, 'file': this.datas });
		}
		
	};

	this.setLabels = function(labels){
		this.labels = isEmpty(labels) ? this.defaultLabel : labels;
		this.params["labels"] = this.labels;
	};

	/**
	 * 恢复位置
	 */
	this.restorePosition = function(){
		DygraphEventHandler.restorePositioning(this.g);
	};
	
	this.getG = function(){
		return this.g;
	};
	
	/**
	 * 显示所有线
	 */
	this.show = function(index){
		if(isNotEmpty(this.g)){
			
			for(var i = 0, n = this.g.visibility().length; i < n; i++){
				if(isNotEmpty(index)){
					if(index == i){
						this.g.setVisibility(i, true);
					}
				} else {
					this.g.setVisibility(i, true);
				}
			}
		}
	};
	
	/**
	 * 隐藏所有线
	 */
	this.hide = function(index){
		if(isNotEmpty(this.g)){
			
			for(var i = 0, n = this.g.visibility().length; i < n; i++){
				if(isNotEmpty(index)){
					if(index == i){
						this.g.setVisibility(i, false);
					}
				} else {
					this.g.setVisibility(i, false);
				}
			}
		}
	};

	/**
	 * 放大
	 */
	this.zoomIn = function(){
		DygraphEventHandler.onScroll(null, this.g, null, 3);
	};
	
	/**
	 * 缩小
	 */
	this.zoomOut = function(){
		DygraphEventHandler.onScroll(null, this.g, null, -3);
	};
	
	/**
	 * 清除所有线
	 */
	this.clear = function(){
		this.datas = null;
		this.params["file"] = this.datas;
		this.g.updateOptions(this.params);
	};
	
	this.setColor = function(index, color){
		var colors = this.g.getColors();
		if(index >= 0 && index < colors.length){
			colors[index] = color;
			this.params["colors"] = colors;
			this.g.updateOptions(this.params);
		}
	};
	
	this.setEventListeners = function(eventListeners){
		this.eventListeners = eventListeners;
		this.init();
	};
	
	this.fillGraph = function(fillGraph){
		this.params["fillGraph"] = fillGraph;
		this.g.updateOptions(this.params);
	};

	this.setCallback = function(type, func){
		this.params[type] = func;
		this.g.updateOptions(this.params);
	};

	this.reset = function(){
		this.init();
	};

};