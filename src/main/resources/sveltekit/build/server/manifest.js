const manifest = (() => {
function __memo(fn) {
	let value;
	return () => value ??= (value = fn());
}

return {
	appDir: "_app",
	appPath: "_app",
	assets: new Set([".DS_Store","img/.DS_Store","img/176832626645500.jpg","img/176832708017750.png","img/176845391012916.jpg","img/176845406355208.jpg","img/191095542200125.jpg","img/191095543759333.jpg","img/33963511662500.png","img/34139955665916.png","img/34139956084416.png","img/4409326305291.png","img/4418970349875.png","img/5082631320791.png","img/5082634343083.png","img/5134249582375.png","img/5134261812791.png","img/5134262002666.png","img/5185340912125.png","img/5185342075125.png","img/5185342174833.png","img/5185342250041.png","img/Main.png","img/callgo_logo.png","img/mainphoto.png"]),
	mimeTypes: {".jpg":"image/jpeg",".png":"image/png"},
	_: {
		client: {"start":"_app/immutable/entry/start.CZhxjhju.js","app":"_app/immutable/entry/app.By4QdAuE.js","imports":["_app/immutable/entry/start.CZhxjhju.js","_app/immutable/chunks/entry.BtOm4_Lm.js","_app/immutable/chunks/scheduler.DCPJa09y.js","_app/immutable/chunks/index.WoGXbVwN.js","_app/immutable/entry/app.By4QdAuE.js","_app/immutable/chunks/scheduler.DCPJa09y.js","_app/immutable/chunks/index.OfV0S5WN.js"],"stylesheets":[],"fonts":[],"uses_env_dynamic_public":false},
		nodes: [
			__memo(() => import('./chunks/0-BXy7sLgW.js')),
			__memo(() => import('./chunks/1-C_yf7zCh.js')),
			__memo(() => import('./chunks/2-vbZbNdAd.js')),
			__memo(() => import('./chunks/3-CzROkl4v.js')),
			__memo(() => import('./chunks/4-Bkx4f2Db.js')),
			__memo(() => import('./chunks/5-DXqMjdSA.js')),
			__memo(() => import('./chunks/6-CQ1A96MO.js')),
			__memo(() => import('./chunks/7-BVSYtjRr.js')),
			__memo(() => import('./chunks/8-CJPKxZ26.js')),
			__memo(() => import('./chunks/9-r6LwrcC5.js')),
			__memo(() => import('./chunks/10-BJP-thp2.js')),
			__memo(() => import('./chunks/11-CBwxKmbV.js')),
			__memo(() => import('./chunks/12-COoUMAY2.js')),
			__memo(() => import('./chunks/13-Dd1zI7MS.js'))
		],
		routes: [
			{
				id: "/",
				pattern: /^\/$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 2 },
				endpoint: null
			},
			{
				id: "/admin",
				pattern: /^\/admin\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 3 },
				endpoint: null
			},
			{
				id: "/categorybutton",
				pattern: /^\/categorybutton\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 4 },
				endpoint: null
			},
			{
				id: "/categorydialbutton",
				pattern: /^\/categorydialbutton\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 5 },
				endpoint: null
			},
			{
				id: "/detail",
				pattern: /^\/detail\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 6 },
				endpoint: null
			},
			{
				id: "/footer",
				pattern: /^\/footer\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 7 },
				endpoint: null
			},
			{
				id: "/maincategoryview",
				pattern: /^\/maincategoryview\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 8 },
				endpoint: null
			},
			{
				id: "/mainview",
				pattern: /^\/mainview\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 9 },
				endpoint: null
			},
			{
				id: "/modal",
				pattern: /^\/modal\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 10 },
				endpoint: null
			},
			{
				id: "/navbar",
				pattern: /^\/navbar\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 11 },
				endpoint: null
			},
			{
				id: "/productregistration",
				pattern: /^\/productregistration\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 12 },
				endpoint: null
			},
			{
				id: "/shoppingbasket",
				pattern: /^\/shoppingbasket\/?$/,
				params: [],
				page: { layouts: [0,], errors: [1,], leaf: 13 },
				endpoint: null
			}
		],
		matchers: async () => {
			
			return {  };
		},
		server_assets: {}
	}
}
})();

const prerendered = new Set([]);

const base = "";

export { base, manifest, prerendered };
//# sourceMappingURL=manifest.js.map
