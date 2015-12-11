
package rebecca.e10.pipes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import rebecca.e10.core.Pipe;
import rebecca.e10.core.Slot;
import rebecca.e10.datasets.ObrazC1;
import rebecca.e10.datasets.SuperPixel;

public class SegmentatorC1 extends Pipe {

	private Slot<ObrazC1> input;
	private Slot<SuperPixel> output;
	private Slot<String[]> par;
	ObrazC1 I = null;
	ObrazC1 T = null;
	SuperPixel O = null;
	double sigma;
	double k;
	String[] stt;
	int min;
	Object l = new Object();

	public SegmentatorC1(Slot<ObrazC1> input, Slot<String[]> par,
			Slot<SuperPixel> output) {
		super("Image Segmentation");

		if ((input != null) & (output != null & par != null)) {

			this.input = input;
			this.par = par;
			this.registerInSlot(input);
			this.registerInSlot(par);

			this.output = output;
			this.registerOutSlot(output);
		} else {
			this.setDamage();
		}
	}

	public void Execute() {

		I = this.input.GetDATA(this).Clone();

		int H = I.getHeight();
		int W = I.getWidth();

		O.setSize(H, W);
		Runtime r = Runtime.getRuntime();
		Process p1 = null;

		stt = this.par.GetDATA(this);

		this.sigma = Double.parseDouble(stt[0]);
		this.k = Integer.parseInt(stt[1]);
		this.min = Integer.parseInt(stt[2]);

		InputStream is;
		OutputStream os;
		//
		synchronized (l) {
			// int ch=0;
			try {
				p1 = r
				// .exec("K:\\[WORK]\\---pool\\Rebecca-E-0.10\\lib\\SEG.exe "
						.exec(".\\lib\\SEG.exe " + H + " " + W + " "
								+ this.sigma + " " + this.k + " " + this.min
								+ " 1");

				/**
				 * 
				 * 
				 * Copyright (C) 2006 Pedro Felzenszwalb
				 * 
				 * http://people.cs.uchicago.edu/~pff/segment/segment.zip
				 * 
				 * This program is free software; you can redistribute it and/or
				 * modify it under the terms of the GNU General Public License
				 * as published by the Free Software Foundation; either version
				 * 2 of the License, or (at your option) any later version.
				 * 
				 * This program is distributed in the hope that it will be
				 * useful, but WITHOUT ANY WARRANTY; without even the implied
				 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
				 * PURPOSE. See the GNU General Public License for more details.
				 * 
				 * You should have received a copy of the GNU General Public
				 * License along with this program; if not, write to the Free
				 * Software Foundation, Inc., 59 Temple Place, Suite 330,
				 * Boston, MA 02111-1307 USA
				 * 
				 * 
				 * #include <cstdio> #include <cstdlib> #include <image.h>
				 * #include <misc.h> #include <pnmfile.h> #include
				 * "segment-image.h" #include <convolve.h> #include
				 * <disjoint-set.h> #include <filter.h> #include <imconv.h>
				 * #include <imutil.h> #include <segment-graph.h> #include<string>
				 * //#include<time.h> #include <iostream> //#include
				 * <algorithm> #include<vector> //#include <functional>
				 * //#include <fstream>
				 * 
				 * 
				 * int main(int argc, char **argv) { if (argc < 7) {
				 * fprintf(stderr, "usage: H W sigma K min chan\n", argv[0]);
				 * return 1; }
				 * 
				 * 
				 * 
				 * int H = atoi(argv[1]); int W = atoi(argv[2]); float sigma =
				 * atof(argv[3]); float k = atof(argv[4]); int min_size =
				 * atoi(argv[5]); int m0 = atoi(argv[6]);
				 * 
				 * 
				 * 
				 * 
				 * std::vector<std::vector<float>> matrix; matrix.resize(H);
				 * 
				 * for (int i=0; i<H; i++) { matrix[i].resize(W); }
				 * 
				 * int s;
				 * 
				 * for (int i=0; i<H; i++) { for (int j=0; j<W; j++) {
				 * std::cin>>matrix[i][j]; //std::cin>>matrix[i][j]; //=s;
				 *  } }
				 * 
				 * edge *edges = new edge[W*H*4]; int num = 0;
				 * 
				 * float f =0;;
				 * 
				 * int num_ccs;
				 * 
				 * 
				 * for(int i=0;i<H;i++){ for(int j=0;j<W;j++){
				 * 
				 * 
				 * 
				 * if(j+1<W){ edges[num].a = (i)*W+(j);//matrix[i][j];
				 * edges[num].b = (i)*W+(j+1);
				 * edges[num].w=abs(matrix[i][j]-matrix[i][j+1]); num++;}
				 * 
				 * if(i+1<H){ edges[num].a = (i+1)*W+(j); edges[num].b =
				 * (i)*W+(j); edges[num].w=abs(matrix[i+1][j]-matrix[i][j]);
				 * num++;}
				 * 
				 * if((i+1<H)&&(j+1<W)){ edges[num].a = (i+1)*W+(j+1);
				 * edges[num].b = (i)*W+(j);
				 * edges[num].w=abs(matrix[i+1][j+1]-matrix[i][j]); num++;
				 * 
				 * 
				 * edges[num].a = (i+1)*W+(j); edges[num].b = (i)*W+(j+1);
				 * edges[num].w=abs(matrix[i+1][j]-matrix[i][j+1]); num++;}
				 * 
				 *  }}
				 * 
				 * 
				 * universe *u = segment_graph(W*H, num, edges, k);
				 *  // post process small components for (int i = 0; i < num;
				 * i++) { int a = u->find(edges[i].a); int b =
				 * u->find(edges[i].b); if ((a != b) && ((u->size(a) < min_size) ||
				 * (u->size(b) < min_size))) u->join(a, b); } delete [] edges;
				 * //*num_ccs = u->num_sets();
				 * 
				 * //image<rgb> *output = new image<rgb>(W, H);
				 * 
				 * //ff=a;
				 * 
				 * int *colors = new int[W*H]; //int tt=0; for (int i = 0; i <
				 * W*H; i++) colors[i] = i+1;
				 * 
				 * for(int i=0;i<H;i++){ for(int j=0;j<W;j++){ int comp =
				 * u->find(i*W + j); matrix[i][j]=colors[comp];
				 * 
				 *  }}
				 * 
				 * for (int i=0; i<H; i++) { for (int j=0; j<W; j++) {
				 * //std::cout<<"element nomer "<<i<<" po h i "<<j<<"
				 * po w "<<matrix[i][j]<<std::endl; std::cout<<matrix[i][j]<<std::endl;
				 * //std::cout<<100<<std::endl; } }
				 * 
				 *  // pick random colors for each component
				 * 
				 * delete [] colors; //matrix.erase(); delete u;
				 * 
				 * 
				 * //std::cout<<"-------------------------------------"<<std::endl;
				 * //for(;;){ //std::cin>>H; //s}
				 * 
				 * return 0; }
				 * 
				 * 
				 * 
				 */

				os = p1.getOutputStream();
				is = p1.getInputStream();

				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
						os));
				String tmp = "";
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						bw.write(" " + (int) I.getVal(i, j));

					}

				}
				bw.flush();
				bw.close();

				BufferedReader bf = new BufferedReader(
						new InputStreamReader(is));

				String t = "";
				int mm = 0;
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						t = bf.readLine();

						O.setVal(i, j, (int) Double.parseDouble(t));

					}

				}

				is.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			p1.destroy();

		}

		this.output.PushDATA(O, this);
	}

	public void Init() {

		O = new SuperPixel();
	}

	public void ShutDown() {

	}
}
