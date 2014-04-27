attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_worldView;
varying vec4 v_color;
varying vec2 v_texCoords;


//varying vec4 v_parable;
//varying vec4 v_linear;
varying float f_ynorm;
varying float f_xnorm;
varying vec4 v_beule;



void main() {
    v_color = vec4(1, 1, 1, 1);
    v_texCoords = a_texCoord0;

    //v_parable = vec4(0, (a_position.x*a_position.x * 0.3) + -0.1, 0, 0);
    
    f_ynorm = (a_position.y+3.0)/5.0;
    f_xnorm = (a_position.x+3.0)/5.0;
    //v_linear = vec4(v_ynorm * 0.25, 1, 1, 1);

    v_beule = vec4(f_ynorm/f_xnorm, f_xnorm/f_ynorm -0.5, 1, 1);

    gl_Position = u_worldView * (a_position )  ;
}  
