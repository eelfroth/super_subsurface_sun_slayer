attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_worldView;
varying vec4 v_color;
varying vec2 v_texCoords;

varying vec4 v_parable;
varying vec4 v_linear;
varying float v_ynorm;


void main() {
    v_color = vec4(1.0, 1.0, 1.0, 1.0);
    //v_color = vec4(((a_position.y+2)/2), ((a_position.y+2)/2), (1-(a_position.y+1)/2), 1) + vec4(((a_position.y+1)/4), ((a_position.y+1)/4), ((a_position.y+1)/4), 1);
    v_texCoords = a_texCoord0;

    v_parable = vec4(0.0, (a_position.x*a_position.x * 0.4) + -0.1, 0.0, 0.0);
    
    v_ynorm = (a_position.y+6.0)/2.0;
    v_linear = vec4(v_ynorm * 0.15, 1.0, 1.0, 1.0);

    gl_Position = u_worldView * (a_position * v_linear - v_parable)  ;
}  
