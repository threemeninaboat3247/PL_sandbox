$(function(){
  document.getElementById("inner").addEventListener("click",function(e){
    console.log("#inner listener 1");
  },true);
  document.getElementById("inner").addEventListener("click",function(e){
    console.log("#inner listener 2");
  },true);
  document.getElementById("outer").addEventListener("click",function(e){
    console.log("#outer listener 1");
    // e.stopPropagation();
  },true);
  document.getElementById("outer_sibling").addEventListener("click",function(e){
    console.log("#outer sibling listener 1");
    e.stopPropagation();
  },true);
})
