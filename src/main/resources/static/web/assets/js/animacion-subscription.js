gsap.registerPlugin(ScrollTrigger);

const contenedorHijo=document.querySelector(".container-hijo");











const animacionOurClasses=()=>{
    clicktab2.addEventListener("click",()=>{
        gsap.from(mostrarSeccionTab2,{
            //rotate:360,
            duration:1.5,
            //delay:1,
            opacity:0,
            yPercent:"+=100"
        
        })
    })
    
    clicktab3.addEventListener("click",()=>{
        gsap.from(mostrarSeccionTab3,{
            //rotate:360,
            duration:1.5,
            //delay:1,
            opacity:0,
            yPercent:"+=100"
        
        })
    })
    clicktab4.addEventListener("click",()=>{
        gsap.from(mostrarSeccionTab4,{
            //rotate:360,
            duration:1.5,
            //delay:1,
            opacity:0,
            yPercent:"+=100"
        
        })
    })
    
    clicktab1.addEventListener("click",()=>{
        gsap.from(mostrarSeccionTab1,{
            //rotate:360,
            duration:1.5,
            //delay:1,
            opacity:0,
            yPercent:"+=100"
        
        })
    })

    
    
    
    
}

const t1Choose=gsap
    .timeline()
    .set(contenedorHijo,{ backgroundImage : 'url(assets/images/fondo-SUBCRIPTIONwebp.webp.jpeg)',delay:0,
    duration:2,
    opacity:0 })
    

     
    .to(contenedorHijo,{
        borderRadius:"10px",
        delay:0,
        duration:1,
        opacity:.6,

    })
    
    .to(contenedorHijo,{
        borderRadius:"10px",
        delay:0,
        duration:1,
        opacity:0
    })
    
    .to(contenedorHijo,{
        backgroundImage :"none",
        backgroundColor:"white",
        borderRadius:"20px",
        delay:0,
        duration:1,
        opacity:1
    })
    .to(".titulo1",{
        display:"none"
        
    })
    .to(".titulo01",{
        display:"none",
        

    })
    .to(".titulo2",{
        display:"none",
    })
    
    .to(".img-Subscrip",{
        display:"flex",
        duration:1,
        opacity:1,
    })
    .to(".container-imput",{
        display:"flex",
        duration:0,
        opacity:1,
    })












    

   


