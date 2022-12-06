gsap.registerPlugin(ScrollTrigger);

const contenedorTitulo=document.querySelector(".animacion-titulo");
const transitionTitle1=document.querySelector(".transition-title1");
const transitionTitle2=document.querySelector(".transition-title2");
const clicktab1=document.querySelector(".animacion-tabs1");
const clicktab2=document.querySelector(".animacion-tabs2");
const clicktab3=document.querySelector(".animacion-tabs3");
const clicktab4=document.querySelector(".animacion-tabs4");
const mostrarSeccionTab1=document.querySelector(".animacion-tabsImag1");
const mostrarSeccionTab2=document.querySelector(".animacion-tabsImag2");
const mostrarSeccionTab3=document.querySelector(".animacion-tabsImag3");
const mostrarSeccionTab4=document.querySelector(".animacion-tabsImag4");
const animationTituloNav=document.querySelector(".animation-titulo-nav");
const animationTituloNav1=document.querySelectorAll(".animation-titulo-nav1");
const animationTituloNav2=document.querySelector(".animation-titulo-nav2");
const animationChooseContainer=document.querySelector(".animacion-choose-container");
const animationChoose=document.querySelectorAll(".animacion-choose");
const animationChoose2=document.querySelector(".animacion-choose2");









const mostrarTitulo=()=>{
gsap.to(contenedorTitulo,{
    //rotate:360,
    duration:3,
    delay:2,
    opacity:0,
    yPercent:"+=100",
   // display:"none"

})

gsap.to(transitionTitle1,{
    duration:5,
    delay:5,
    xPercent:"+=30",
    
}
)

gsap.to(transitionTitle2,{
    duration:5,
    delay:5,
   xPercent:"+=-70",
    
}
)
gsap.set(animationTituloNav,{
    duration:3,
    delay:0,
    //yPercent:"+=-90",
    opacity:0
    
    
}
)
gsap.to(animationTituloNav,{
    duration:3,
    delay:1,
    //yPercent:"+=-90",
    opacity:1

    
}
)
gsap.from(animationTituloNav1,{
    duration:3,
    delay:1,
    yPercent:"+=-110",
    

    
}
)
gsap.set(animationTituloNav2,{
    duration:3,
    delay:0,
    //yPercent:"+=-90",
    opacity:0
    
    
}
)
gsap.to(animationTituloNav2,{
    duration:3,
    delay:1,
    //yPercent:"+=-90",
    opacity:1

    
}
)


}


    



mostrarTitulo();
