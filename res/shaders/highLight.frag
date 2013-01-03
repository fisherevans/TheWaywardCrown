#version 120 
uniform sampler2D tex0;
void main() {
        vec4 light = texture2D(tex0, gl_TexCoord[0].st);
        vec4 final = vec4(0.0, 0.0, 0.0, 1.0);
        final.r = max(gl_Color.r, light.r);
        final.g = max(gl_Color.g, light.g);
        final.b = max(gl_Color.b, light.b);
        gl_FragColor = gl_Color;
}