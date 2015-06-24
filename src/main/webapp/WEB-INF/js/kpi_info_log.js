	$.getMenu= function(firUrl){	
		if(firUrl!=""){		
			$.ajax({
				    async: false,
				    url  : firUrl+"/data/kpiInfoHistory/getEntityProperty",
					type : 'post',
					datatype:'json',
					success: function(data, status, xhr) {
						
						if (data.success == true&&data.result.fieldProperties &&JSON.stringify(data.result.fieldProperties) != "[]") 
						{
							$.each(data.result.fieldProperties,function(entryIndex,entry) 
									{
									if(entry.operate=="select")
										{
										
										if(entry.path=="resourceRequirement")
											{
												$.analyzeSelMenu("myresourceRequirement",entry.option);
												return true;
											}
										
										$.analyzeSelMenu(entry.path,entry.option);	
										}	
							});
						}
					}
				});
				
		    }
			
		  };
		  $.analyzeSelMenu= function(dataname,_obj)
			{
				for ( var sitem in _obj) 
				{
				if($("[name="+dataname+"]").length > 0 )
					{
					$("[name="+dataname+"]").append("<option value='"+sitem+"'>"+_obj[sitem]+"</option>"); 
					}
				}
			};
	//查询 --history1
	 $.postSearchForm = function(firUrl,paging){
			paging+="&sort=id desc";
			if(firUrl!=""){	
				$.ajax({
					async: false,
					url : firUrl+"/data/kpiInfoHistory/page",
					type : 'post',
					data: $("#kpi_infoList").serialize()+paging,
					success: function(data, status, xhr){
						
						$.analyzeTableData(data, status, xhr,firUrl);
						$.setBtn(firUrl);
						}, 
					error: function() {
						hiAlert("对不起！你的请求出现异常，请联系服务人员！","重要提示");
				    } 
			     });
			}
	};

	//解析TABLE
	$.analyzeTableData= function(data, status, xhr,firUrl) {
		if (status == "success" && data != null){
			$("tr.tableItem").remove();
			if (data.success == true&&data.result.content &&JSON.stringify(data.result.content) != "[]")   
						        		{
						        			$("tr.tableItem").remove();  
						        			var tableItem="";
						        			
						        			$.each(data.result.content,function(entryIndex,entry)   
						        			{
						        				if(entry.kpiCode == null){  
						        					entry.kpiCode = "";  
						        				}
						        				if(entry.kpiName == null){
						        					entry.kpiName = "";//entry.isAssess
						        				}
						        				tableItem = "<tr class='tableItem'>";//$("select[name=kpiType]:eq(0)").find("option[value="+entry.kpiType+"]").text()
						        				tableItem+="<td>"+$("select[name=businessType]:eq(0)").find("option[value="+entry.businessType+"]").text()+"</td><td>"+$("select[name=kpiType]:eq(0)").find("option[value="+entry.kpiType+"]").text()+"</td><td>"+entry.kpiName+"</td><td>"+entry.kpiCode+"</td><td>";
						        				tableItem+=$("select[name=isAssess]:eq(0)").find("option[value="+entry.isAssess+"]").text()+"</td><td>"+entry.year+"</td>";
						        				tableItem+="</td><td>"+entry.kpiDefinition+"</td><td>"+entry.kpiAlgorithm+"</td>";   
//						        				tableItem+="<td><a href='javascript:void(0)' onclick='test(\""+entry.kpiId+"\")'  style='text-decoration:underline;color:black;'>详细信息</a></td>"
						        				$(".table_1").append(tableItem);
						        				
						        			});
						        			$.setPage(data.result);  
						        			
						        		}
						        		
						        	}
						        };

	
						  				        
	//设置页码
						    	$.setPage = function(result) {
						    		if (result.pageInfo != null) {
						    			var _pageinfo = result.pageInfo;
						    			var nowcount = $(".table_1").find(".tableItem");

						    			if (_pageinfo.pageSize > nowcount.length) {
						    				var minMum = _pageinfo.pageSize - nowcount.length;
						    				for ( var i = 0; i < minMum; i++) {
						    					$(".table_1")
						    							.append(
						    									"<tr class=\"tableItem\"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
						    				}
						    			}

						    			$(".tfoot span:first-child").text(
						    					"共" + _pageinfo.totalRecord + "条记录，当前显示1-" + nowcount.length
						    							+ "条");

						    			var _menu = "<li>Pages:"
						    					+ _pageinfo.currentPage
						    					+ "/"
						    					+ _pageinfo.totalPages
						    					+ "<input type=\"number\" id=\"gopage\" name=\"number\" min=\"0\" max=\""
						    					+ _pageinfo.totalPages
						    					+ "\" step=\"1\" class=\"input_tiny\" />";
						    			_menu += " <input type=\"button\" name=\"button\" id=\"gopagebtn\" value=\"Go\"></li><li><a class=\"nextpage\">&gt;&gt;</a></li>";

						    			var pageStyle = "";
						    			if (_pageinfo.totalPages > 7) {
						    				if (_pageinfo.currentPage <= 1) {
						    					/*pageStyle += "<li class=\"selected\"><a class=\"topage\">1</a></li> <li><a class=\"topage\">2</a></li> <li><a class=\"topage\">3</a></li> <li><a class=\"topage\">4</a></li> <li><a class=\"topage\">5</a></li> <li><a>...</a></li> <li><a>"
						    							+ _pageinfo.totalPages + "</a></li>";*/
						    					pageStyle += "<li><a class=\"topage\">4</a></li> <li><a class=\"topage\">3</a></li> <li><a class=\"topage\">2</a></li> <li class=\"selected\"><a class=\"topage\">1</a></li>";
						    				} else {
						    					var pa = _pageinfo.currentPage - 1;
						    					pb = _pageinfo.currentPage + 1;
						    					pc = _pageinfo.currentPage + 2;
						    					pd = _pageinfo.currentPage + 3;
						    					paa = _pageinfo.currentPage - 2;
						    					paaa = _pageinfo.currentPage - 3;
						    					if(pc<=_pageinfo.totalPages){
						    						pageStyle += "<li><a class=\"topage\">" + pc
						    								 + "</a></li> <li><a class=\"topage\">" + pb
						    								 + "</a></li> <li class=\"selected\"><a class=\"topage\">"
						    								 + _pageinfo.currentPage
						    								 + "</a></li> <li><a class=\"topage\">" + pa
						    								 + "</a></li>";
						    					}else if(pc==_pageinfo.totalPages+1){
						    						pageStyle += "<li><a class=\"topage\">" + _pageinfo.totalPages
						    								 + "</a></li> <li class=\"selected\"><a class=\"topage\">" + _pageinfo.currentPage
						    								 + "</a></li> <li><a class=\"topage\">"
						    								 + pa
						    								 + "</a></li> <li><a class=\"topage\">" + paa
						    								 + "</a></li>";
						    					}else{
						    						pageStyle += "<li class=\"selected\"><a class=\"topage\">" + _pageinfo.currentPage
						    								 + "</a></li> <li><a class=\"topage\">" + pa
						    								 + "</a></li> <li><a class=\"topage\">"
						    								 + paa
						    								 + "</a></li> <li><a class=\"topage\">" + paaa
						    								 + "</a></li>";
						    					}
						    				}
						    			} else {
						    				for ( var j = 1; j <= _pageinfo.totalPages; j++) {
						    					if (j == _pageinfo.currentPage) {
						    						pageStyle = "<li class=\"selected\"><a>" + j + "</a></li>"
						    								+ pageStyle;

						    					} else {
						    						pageStyle = "<li><a class=\"topage\">" + j + "</a></li>"
						    								+ pageStyle;

						    					}
						    				}
						    			}

						    			_menu += pageStyle + "<li><a class=\"prepage\">&lt;&lt;</a></li>";
						    			$("ul.pager li").remove();
						    			$("ul.pager").append(_menu);
						    		}
						    	};
						        
	 $.setBtn = function(firUrl){
							       	 $(".topage").click(function(){
							        		$.postSearchForm(firUrl,"&pageSize=15&pageNum="+$(this).text());//,isleader
							        		
							       		});
							       	   
							           $(".prepage").click(function(){
							        	 var nowpage=parseInt($("#page>.selected a").text());

							        	 if(nowpage>1)
							        		 {
							        		 var topage=nowpage-1;
							        		$.postSearchForm(firUrl,"&pageSize=15&pageNum="+topage);//,isleader
							        		 }
							       		}); 
							         
							          $(".nextpage").click(function(){
							        	 var nextpage=parseInt($("#page>.selected a").text())+1;

							        	 if(nextpage<=parseInt($("#gopage").attr("max")))
							       		   $.postSearchForm(firUrl,"&pageSize=15&pageNum="+nextpage);//,isleader

							       		}); 
							          $("#gopagebtn").click(function(){
							        	  var topage=parseInt($("#gopage").val());
							          
							            	 if(topage<=parseInt($("#gopage").attr("max"))&&topage>=1)
							        		 {
							        		$.postSearchForm(firUrl,"&pageSize=15&pageNum="+topage);//,isleader
							        		 }
							            	 else
							            		 {
							            		 alert("超出索引值");
							            		 }
							       			});  
							       };
