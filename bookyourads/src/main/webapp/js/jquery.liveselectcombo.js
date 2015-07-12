/*
 * jQuery Custombox Plugin
 *  MIT License - http://www.opensource.org/licenses/mit-license.php
 *  GPL License - http://www.opensource.org/licenses/gpl-2.0.php
 */
(function(d){
	var a=false;d.custombox={
		dsName:"custombox",defaults:{
			boxType:"select",boxClass:"custombox",containerClass:"custombox-wrapper",contextMenuDisabled:true,openOnTab:false,menuOpen:false,menuNumItems:10,menuMaxHeight:"200px",calculateMenuMaxHeight:false,debug:false},settings:function(l){
				if(arguments.length>0){
					var m={},k=l.data(d.custombox.dsName),j=arguments[1];d.extend(m,k,j);
					l.data(d.custombox.dsName,m);
					return l.data(d.custombox.dsName)
				}else{
					return l.data(d.custombox.dsName)
				}
			},enableContextMenu:function(j){
				j[0].oncontextmenu=function(){
					return true
				};
				d.custombox.settings(j,{
					contextMenuDisabled:false
				});
				return d.custombox.settings(j)
			},disableContextMenu:function(j){
				j[0].oncontextmenu=function(){
					return false
				};
				d.custombox.settings(j,{
					contextMenuDisabled:true
				});
				return d.custombox.settings(j)
			}
		};
	d.fn.extend({
				accessibleHide:function(){return d(this).each(function(){d(this).addClass("accessibleHide")})}
	});
	d.fn.extend(
		{custombox:function(j){boxoptions=d.extend({},d.custombox.defaults,j);d("html").click(
			function(){
				d("div."+d.custombox.defaults.boxClass).each(function(){if(d(this).settings().menuOpen){h(d(this))}});g()
			}
			);
			d("textarea, :text, :password, :radio, :checkbox, :button").focus(function(){g();c()});
			return d(this).each(function(){
				//Start of Custome Select Box Code
				var o=d(this);
				var r=(o.width());
				var n=r+5;
				if(o.children().length<1){
					d("<option></option>").appendTo(o)
				}
				var v=d("<div></div>").addClass(boxoptions.boxClass).removeData(d.custombox.dsName).data(d.custombox.dsName,boxoptions).settings({sourceID:o.attr("id")}).css("width",n+"px");
				var q=d('<ul class="list"></ul>');
				b(o,v,q,v.settings().sourceID);
				var k=d('<div class="menu"></div>').append(q);
				var s=d('<div id="cb_'+v.settings().sourceID+'" class="'+v.settings().containerClass+'"></div>').append(k).css("width",r - 35 + "px").hide();
				var p=d('<a href="#" tabindex="-1" hidefocus="true"><span id="span_'+v.settings().sourceID+'"></span></a>').css('background-position',n - 30+ "px center");
				if(o.attr("disabled")){
					o.parents(".item").children().addClass("disabled")}p.children("span").text(q.find("li a.selected").text());
					v.append(p);
					var hiddenBox = document.createElement("input");
					hiddenBox.id='hidden_'+v.settings().sourceID+'';
					hiddenBox.type="hidden";
					hiddenBox.value=o.children()[0].text;
					v.append(hiddenBox);
					if(d.browser && d.browser.msie && parseInt(d.browser.version) <= 7) {
		  				//var baseProt = window.location.protocol;
						//modified for ECR 231
						var baseProt="https:"
			  			var m=d('<iframe src="'+baseProt+'//www.ally.com/files/pres/images/logo.png" tabindex="-1" hidefocus="true" border="1"></iframe>');
		  				p.after(m);}
						o.after(v).accessibleHide();
						v.after(s);
						if(v.settings().contextMenuDisabled){
							v.disableContextMenu()
						}
						if(v.settings().calculateMenuMaxHeight){
							var u=(v.settings().menuNumItems*parseInt(q.find("li.first a span").css("height")))+"px"
						}else{
							var u=v.settings().menuMaxHeight
						}
						if(o.children("option").length<=v.settings().menuNumItems){
							s.height("auto")
						}else{
							s.height(u)
						}
						p.click(function(){
							$('.' + v.settings().containerClass + ':visible').click();							
							if(o.data('callBackPresent'))
								o.data('callBackPresent')(d(o));
							if(!o.parents(".control").hasClass("disabled")){
								d(".hasfocus :input").trigger("blur");
								o.triggerHandler("click")
							}
							return false
						}).mousedown(function(){
								return false
							});
						o.focus(function(){//alert("focus")
							g();
							c();
							e(v);
							if(v.settings().openOnTab){
								i(v)
							}
						}).click(function(){
							if(v.settings().menuOpen){
								h(v)
							}else{
								e(v);
								i(v)
							}
							o.trigger("focus");
							return false
						}).blur(function(){
						}).change(function(){
							q.find("li a.selected").removeClass("selected");
							//alert("q "+q.val());
							var w=q.children().eq(d(this)[0].selectedIndex).children("a");
							w.addClass("selected");
							v.settings({selectedMenuItemID:w.attr("id")});
							o.next().find("a span").text(w.text())
						}).keyup(function(w){
							var key=w.keyCode;
							if ((key >= 65 && key <= 90) || (key >= 97 && key <= 123) || (key>=48 && key<=57))
							{
								if(!$.browser.msie || ($('.monacoPage').length > 0)){
									d(this).trigger("change")
								}
							}						
						}).keydown(function(y,z){
						if(y.keyCode){
								var w=y.keyCode;
								if(w==40||w==39){
									d(this).moveMenuSelectionDown()
								}else{
									if(w==38||w==37){
										d(this).moveMenuSelectionUp()
									}else{
										if(w==35||w==34){
											if(q.find("li a.selected").length==1){
												q.find("li a.selected").removeClass("selected")
											}
											var x=q.find("li.last a");
											x.addClass("selected");
											v.settings({
												selectedMenuItemID:x.attr("id")
											});
												//alert("text here " +x.text());
											v.find("a span").text(x.text())
										}else{
											if(w==36||w==33){
												if(q.find("li a.selected").length==1){
													q.find("li a.selected").removeClass("selected")
												}
												var A=q.find("li.first a");
												A.addClass("selected");
												v.settings({selectedMenuItemID:A.attr("id")});
												v.find("a span").text(A.text())
											}else{
												if(w==13||w==27||w==9){
													h(v)
												}
											}
										}
									}
								}
							}else{
								var w=z;
								if(w==40){
									d(this).moveMenuSelectionDown()
								}else{
									if(w==38){
										d(this).moveMenuSelectionUp()
									}else{
										if(w==39){
											window.event.keyCode=40;
											d(this).moveMenuSelectionDown()
										}else{
											if(w==37){
												window.event.keyCode=38;
												d(this).moveMenuSelectionUp()
											}else{
												if(w==9){}
											}
										}
									}
								}
							}							
						}
					)
				}
			)
		},settings:function(){
			if(arguments.length>0){
				d.custombox.settings(d(this),arguments[0]);
				return d(this)
			}else{
				return d.custombox.settings(d(this))
			}
		},enableContextMenu:function(){
			d.custombox.enableContextMenu(d(this));
			return d(this)
		},disableContextMenu:function(){
			d.custombox.disableContextMenu(d(this));
			return d(this)
		},updateOptions:function(){
			d(this).each(function(m){
				var o=d(this)[m].id,l=d(this),n=l.next(),p=n.next();
				if(l.children("option").length<=10){
					p.height("auto")
				}else{
					p.height(n.settings().menuMaxHeight)
				}
				var k=n.next("#cb_"+d(this)[m].id).find(".list");
				k.empty();
				b(l,n,k,o);
				var j=k.children().eq(d(this)[0].selectedIndex).children("a");
				l.next().find("a span").text(j.text())
			})
		},toggleError:function(){
			d(this).each(function(j){
				d(this).closest(".control").toggleClass("error")
			})
		},disableControl:function(){
			if(arguments.length>0){
				var j=arguments[0].clearFields
			}else{
				var j=false}d(this).each(function(){
					d(this).attr("disabled","disabled").parents(".item").children().addClass("disabled");
					d(this).parents(".item").find("label").addClass("disabled");
					if(j){
						if(d(this)[0].type=="select-one"){
							d(this).children("option").each(function(){
								if(d(this)[0].defaultSelected){
									d(this).attr("selected","selected")
								}else{
									d(this).removeAttr("selected")
								}
							});
							d(this).updateOptions()
						}else{
							if(d(this).is(":text")||d(this).is(":password")){
								d(this).val("")
							}
						}
					}
				}
			)
		},enableControl:function(){
			d(this).each(function(j){
				d(this).removeAttr("disabled").closest(".item").find(".disabled").removeClass("disabled")
			})
		},moveMenuSelectionDown:function(){
			var p=d(this)[0].id,l=d(this),o=l.next();
			var n=o.next("#cb_"+d(this)[0].id);
			var k=n.find(".list");
			if(k.find("li a.selected").parent().next().is("li")){
				if(k.find("li a.selected").length==1){
					var m=k.find("li a.selected").parent().next().children()
				}else{
					var m=k.find("li:first a").parent().next().children()
				}
				k.find("li a.selected").removeClass("selected");
				m.addClass("selected");
				o.settings({
					selectedMenuItemID:m.attr("id")
				});
				o.find("a span").text(m.text());
				if(o.settings().menuOpen){
					var j=m.position().top+m.innerHeight()-10;
					if(j>n.innerHeight()){
						n.scrollTop(n.scrollTop()+20)
					}
				}
			}
		},moveMenuSelectionUp:function(){
			var o=d(this)[0].id,l=d(this),n=l.next();
			var m=n.next("#cb_"+d(this)[0].id);
			var k=m.find(".list");
			if(k.find("li a.selected").parent().prev().is("li")){
				if(k.find("li a.selected").length==1){
					var p=k.find("li a.selected").parent().prev().children();
					k.find("li a.selected").removeClass("selected")
				}else{
					$current=k.find("li a")[0];
					var p=$current.parent().prev().children()}p.addClass("selected");
					n.settings({selectedMenuItemID:p.attr("id")});
					n.find("a span").text(p.text());
					if(n.settings().menuOpen){
						var j=p.position().top;
						if(j<m.scrollTop()){
							m.scrollTop(m.scrollTop()-20)
						}
					}
				}
			}		
			
		});
	function b(k,l,j,m){
		k.children("option").each(function(o){
			if(o==0){
				var p=d('<li class="first"></li>')
			}else{
				if(o==(k[0].options.length-1)){
					var p=d('<li class="last"></li>')
				}else{
					var p=d("<li></li>")
				}
			}
			if(d(this).is(":selected")){
				l.settings({
					selectedMenuItemID:d(this).attr("id")
				})
			}else{
				l.settings({
					selectedMenuItemID:m+"_1"
				})
			}
			var cls="";
			//var value = "";
			//value=d(this).value;
			//var textval =d(this).text();
			//alert("value here is: " + value);
		   if(o%2)cls="";else cls="odd ";
		   if(d(this).is(":selected"))
		   cls=cls+"selected";
		   var n=d('<a href="#" tabindex="-1" class="'+cls+'" hidefocus="true" id="'+m+"_"+d(this).val()+'"'+"><span>"+d(this).text()+"</span></a>").mousedown(function(){
		    j.find("li a.selected").removeClass("selected")
			
			}).mouseup(function(r){
				d(this).addClass("selected");
				//alert("value here is::: " + value);
				//alert("text here is::: " + textval);
				//d(this).val(value);
				//var idvalue = d(this).attr("id");
				//var dropId = idvalue.substring(0,idvalue.indexOf('_'));
				//document.getElementById(dropId).value = value;
				l.settings({selectedMenuItemID:d(this).attr("id")});
				k.next().find("a span").text(d(this).text());
				var q=d(this).text(),s=d(this).attr("id");
				d("#"+s.substring(0,s.lastIndexOf("_"))+" > option").each(function(t){if(d(this).val() === (s.substring(s.lastIndexOf('_')+1))) { k[0].selectedIndex = t; }});
				h(l);
				//alert("h(l) " + document.getElementById("motorInventoryState").value);
				
				k.trigger("change").trigger("focus");
				return false
			}).mouseover(function(){
				j.find("li a.selected").removeClass("selected").addClass("wasselected")
			}).click(function(){
				return false
			});
			p.append(n);
			j.append(p); 
		})
	}
	function h(j){
		var k=j.next("."+j.settings().containerClass);
		if(k.find("li a.selected").length>0){
			k.find("li a.wasselected").removeClass("wasselected")
		}else{
			k.find("li a.wasselected").addClass("selected").removeClass("wasselected")
		}
		j.next("."+j.settings().containerClass).slideUp(100,function(){
			j.settings({
				menuOpen:false
			}).parents(".item").css("z-index","1")
		})
	}
	function i(j){
		j.parents(".item").css("z-index","99");
		j.next("."+j.settings().containerClass).stop(false,true).slideDown("fast",function(){
			j.settings({menuOpen:true});
			var l=j.next("."+j.settings().containerClass);
			if(l.find("li a.selected").length>0){
				var k=l.find("li a.selected").position().top
			}else{
				if(l.find("li a.wasselected").length>0){
					var k=l.find("li a.wasselected").position().top
				}else{
					var k=0
				}
			}
			if(k>=l.innerHeight()){
				l.scrollTop(k)
			}else{
				l.scrollTop(0)
			}
		})
	}
	function c(){
		d("div."+d.custombox.defaults.boxClass).each(function(){
			if(d(this).settings().menuOpen){
				h(d(this))
			}
		})
	}
	function g(){
		d("div."+d.custombox.defaults.boxClass).each(function(){
			f(d(this))
		})
	}
	function e(j){
		j.parents(".control").addClass("hasfocus")
	}
	function f(j){
		j.parents(".control").removeClass("hasfocus")
	}
})(jQuery);
$(function(){
	if($.browser && $.browser.msie && parseInt($.browser.version) <= 7 ){
		$("form select").keydown(function(b){
			$(this).triggerHandler("keypress",[b.keyCode]);
		});	
	}
});
$(function(){$("textarea, :text, :password").focus(function(){$(this).parents(".control").addClass("hasfocus")}).blur(function(){$(this).parents(".control").removeClass("hasfocus")})});

$(document).ready(function(){
	$("select.customselect").custombox({boxType:"select",containerClass:"custombox-wrapper",boxClass:"custombox",contextMenuDisabled:true,openOnTab:false,debug:false});
});
