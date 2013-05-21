
(function (b) {
	var a = (navigator.userAgent.toLowerCase().indexOf("msie") != -1);
	var d = {setStyle:function (i, g, h) {
		switch (g) {
		  case "opacity":
			if (a) {
				if (i.style.filter.toString().search(/alpha\(opacity=\d+\)/i) > -1) {
					i.style.filter = i.style.filter.replace(/alpha\(opacity=\d+\)/i, "alpha(opacity=".concat(h | 0).concat(")"));
				} else {
					i.style.filter += "alpha(opacity=".concat(h | 0, ")");
				}
			} else {
				i.style.opacity = (h / 100).toString();
			}
			break;
		  default:
			i.style[g] = h + "px";
			break;
		}
	}, getStyle:function (i, g) {
		var h = i.currentStyle ? i.currentStyle[g] : document.defaultView.getComputedStyle(i, null)[g];
		switch (g) {
		  case "opacity":
			if (a) {
				return Number(i.currentStyle.filter.replace(/alpha\(opacity=(\d+)\)/i, "$1")) || 100;
			} else {
				return (Number(h) || 1) * 100;
			}
		  default:
			return (h.replace("px", "") | 0) || 0;
		}
	}};
	var f = function (g) {
		return new c(g);
	};
	f.Line = function (h, g, j, i) {
		return j * h / i + g;
	};
	f.Quad = {easeIn:function (h, g, j, i) {
		return j * (h /= i) * h + g;
	}, easeOut:function (h, g, j, i) {
		return -j * (h /= i) * (h - 2) + g;
	}, easeInOut:function (h, g, j, i) {
		if ((h /= i / 2) < 1) {
			return j / 2 * h * h + g;
		}
		return -j / 2 * ((--h) * (h - 2) - 1) + g;
	}};
	f.Cubi = {easeIn:function (h, g, j, i) {
		return j * (h /= i) * h * h + g;
	}, easeOut:function (h, g, j, i) {
		return j * ((h = h / i - 1) * h * h + 1) + g;
	}, easeInOut:function (h, g, j, i) {
		if ((h /= i / 2) < 1) {
			return j / 2 * h * h * h + g;
		}
		return j / 2 * ((h -= 2) * h * h + 2) + g;
	}};
	f.Quar = {easeIn:function (h, g, j, i) {
		return j * (h /= i) * h * h * h + g;
	}, easeOut:function (h, g, j, i) {
		return -j * ((h = h / i - 1) * h * h * h - 1) + g;
	}, easeInOut:function (h, g, j, i) {
		if ((h /= i / 2) < 1) {
			return j / 2 * h * h * h * h + g;
		}
		return -j / 2 * ((h -= 2) * h * h * h - 2) + g;
	}};
	f.Quin = {easeIn:function (h, g, j, i) {
		return j * (h /= i) * h * h * h * h + g;
	}, easeOut:function (h, g, j, i) {
		return j * ((h = h / i - 1) * h * h * h * h + 1) + g;
	}, easeInOut:function (h, g, j, i) {
		if ((h /= i / 2) < 1) {
			return j / 2 * h * h * h * h * h + g;
		}
		return j / 2 * ((h -= 2) * h * h * h * h + 2) + g;
	}};
	f.Sine = {easeIn:function (h, g, j, i) {
		return -j * Math.cos(h / i * (Math.PI / 2)) + j + g;
	}, easeOut:function (h, g, j, i) {
		return j * Math.sin(h / i * (Math.PI / 2)) + g;
	}, easeInOut:function (h, g, j, i) {
		return -j / 2 * (Math.cos(Math.PI * h / i) - 1) + g;
	}};
	f.Expo = {easeIn:function (h, g, j, i) {
		return (h == 0) ? g : j * Math.pow(2, 10 * (h / i - 1)) + g;
	}, easeOut:function (h, g, j, i) {
		return (h == i) ? g + j : j * (-Math.pow(2, -10 * h / i) + 1) + g;
	}, easeInOut:function (h, g, j, i) {
		if (h == 0) {
			return g;
		}
		if (h == i) {
			return g + j;
		}
		if ((h /= i / 2) < 1) {
			return j / 2 * Math.pow(2, 10 * (h - 1)) + g;
		}
		return j / 2 * (-Math.pow(2, -10 * --h) + 2) + g;
	}};
	f.Circ = {easeIn:function (h, g, j, i) {
		return -j * (Math.sqrt(1 - (h /= i) * h) - 1) + g;
	}, easeOut:function (h, g, j, i) {
		return j * Math.sqrt(1 - (h = h / i - 1) * h) + g;
	}, easeInOut:function (h, g, j, i) {
		if ((h /= i / 2) < 1) {
			return -j / 2 * (Math.sqrt(1 - h * h) - 1) + g;
		}
		return j / 2 * (Math.sqrt(1 - (h -= 2) * h) + 1) + g;
	}};
	f.Elas = {easeIn:function (i, g, m, l, h, k) {
		if (i == 0) {
			return g;
		}
		if ((i /= l) == 1) {
			return g + m;
		}
		if (!k) {
			k = l * 0.3;
		}
		if (!h || h < Math.abs(m)) {
			h = m;
			var j = k / 4;
		} else {
			var j = k / (2 * Math.PI) * Math.asin(m / h);
		}
		return -(h * Math.pow(2, 10 * (i -= 1)) * Math.sin((i * l - j) * (2 * Math.PI) / k)) + g;
	}, easeOut:function (i, g, m, l, h, k) {
		if (i == 0) {
			return g;
		}
		if ((i /= l) == 1) {
			return g + m;
		}
		if (!k) {
			k = l * 0.3;
		}
		if (!h || h < Math.abs(m)) {
			h = m;
			var j = k / 4;
		} else {
			var j = k / (2 * Math.PI) * Math.asin(m / h);
		}
		return (h * Math.pow(2, -10 * i) * Math.sin((i * l - j) * (2 * Math.PI) / k) + m + g);
	}, easeInOut:function (i, g, m, l, h, k) {
		if (i == 0) {
			return g;
		}
		if ((i /= l / 2) == 2) {
			return g + m;
		}
		if (!k) {
			k = l * (0.3 * 1.5);
		}
		if (!h || h < Math.abs(m)) {
			h = m;
			var j = k / 4;
		} else {
			var j = k / (2 * Math.PI) * Math.asin(m / h);
		}
		if (i < 1) {
			return -0.5 * (h * Math.pow(2, 10 * (i -= 1)) * Math.sin((i * l - j) * (2 * Math.PI) / k)) + g;
		}
		return h * Math.pow(2, -10 * (i -= 1)) * Math.sin((i * l - j) * (2 * Math.PI) / k) * 0.5 + m + g;
	}};
	f.Back = {easeIn:function (h, g, k, j, i) {
		if (i == undefined) {
			i = 1.70158;
		}
		return k * (h /= j) * h * ((i + 1) * h - i) + g;
	}, easeOut:function (h, g, k, j, i) {
		if (i == undefined) {
			i = 1.70158;
		}
		return k * ((h = h / j - 1) * h * ((i + 1) * h + i) + 1) + g;
	}, easeInOut:function (h, g, k, j, i) {
		if (i == undefined) {
			i = 1.70158;
		}
		if ((h /= j / 2) < 1) {
			return k / 2 * (h * h * (((i *= (1.525)) + 1) * h - i)) + g;
		}
		return k / 2 * ((h -= 2) * h * (((i *= (1.525)) + 1) * h + i) + 2) + g;
	}};
	f.Boun = {easeIn:function (h, g, j, i) {
		return j - f.Bounce.easeOut(i - h, 0, j, i) + g;
	}, easeOut:function (h, g, j, i) {
		if ((h /= i) < (1 / 2.75)) {
			return j * (7.5625 * h * h) + g;
		} else {
			if (h < (2 / 2.75)) {
				return j * (7.5625 * (h -= (1.5 / 2.75)) * h + 0.75) + g;
			} else {
				if (h < (2.5 / 2.75)) {
					return j * (7.5625 * (h -= (2.25 / 2.75)) * h + 0.9375) + g;
				} else {
					return j * (7.5625 * (h -= (2.625 / 2.75)) * h + 0.984375) + g;
				}
			}
		}
	}, easeInOut:function (h, g, j, i) {
		if (h < i / 2) {
			return f.Bounce.easeIn(h * 2, 0, j, i) * 0.5 + g;
		} else {
			return f.Bounce.easeOut(h * 2 - i, 0, j, i) * 0.5 + j * 0.5 + g;
		}
	}};
	f.fxs = [];
	f.timer = null;
	f.step = function () {
		var g = f.fxs;
		if (!g.length) {
			clearInterval(f.timer);
			f.timer = null;
		}
		for (var h = g.length - 1; h > -1; h--) {
			g[h].next();
		}
	};
	f.add = function (g) {
		f.fxs.push(g);
	};
	f.remove = function (j) {
		var g = f.fxs;
		for (var h = g.length - 1; h > -1; h--) {
			if (g[h] === j) {
				g.splice(h, 1);
			}
		}
	};
	var c = function (g) {
		this[0] = g;
		this.isDOM = (typeof (g.nodeType) === "number");
		this.queue = [];
		this.isMoving = 0;
		this.tweenType = f.Line;
	};
	c.prototype = {setStep:function (g) {
		this._step = g;
		return this;
	}, setTweenType:function (g) {
		this.tweenType = g;
		return this;
	}, dequeue:function () {
		var g = this.queue.shift();
		g && g.start();
	}, to:function (h, k, l, g) {
		if (arguments.length == 3 && typeof (l) == "number") {
			g = l;
			l = 0;
		}
		var i = this.isDOM;
		var j = new e({parent:this, target:this[0], to:h, tweenType:this.tweenType, step:this._step, callback:l, setProperty:(i ? d.setStyle : 0), getProperty:(i ? d.getStyle : 0), duration:k, delay:g});
		if (this.isMoving) {
			this.queue.push(j);
		} else {
			j.start();
		}
		return this;
	}, stopSingle:function () {
		this.queue = [];
		var g = f.fxs;
		for (var h = g.length - 1; h > -1; h--) {
			if (g[h].parent === this) {
				g.splice(h, 1);
			}
		}
		return this;
	}, stop:function () {
		var g = f.fxs;
		for (var h = g.length - 1; h > -1; h--) {
			if (g[h].target === this[0]) {
				g[h].parent.stopSingle();
			}
		}
		return this;
	}, toString:function () {
		return "[object TweenProto]";
	}};
	var e = function (g) {
		this.parent = g.parent;
		this.target = g.target;
		this.to = g.to;
		this.duration = g.duration;
		this.setProperty = g.setProperty;
		this.getProperty = g.getProperty;
		this.tweenType = g.tweenType;
		this.callback = g.callback;
		this.delay = g.delay;
		this.step = g.step;
		this.changeProperties = [];
		this.begin = {};
		this.change = {};
	};
	e.prototype = {start:function () {
		if (this.parent) {
			this.parent.isMoving = 1;
		}
		var g = this;
		if (this.delay) {
			return setTimeout(function () {
				f.add(g);
				g.init(g.to);
			}, this.delay);
		}
		f.add(this);
		this.init(this.to);
	}, init:function (i) {
		var h = this.changeProperties;
		for (var g in i) {
			h.push(g);
			this.begin[g] = this.getProperty ? this.getProperty(this.target, g) : this.target[g];
			this.change[g] = this.to[g] - this.begin[g];
		}
		this.start = new Date();
		if (f.fxs.length && !f.timer) {
			f.timer = setInterval(f.step, 25);
		}
	}, next:function () {
		var o = this.changeProperties, k = new Date();
		if ((k - this.start) > this.duration) {
			for (var l = 0, h = o.length, n; l < h; l++) {
				n = o[l];
				if (this.setProperty) {
					this.setProperty(this.target, n, this.to[n]);
				} else {
					this.target[n] = this.to[n];
				}
			}
			this.parent && this.parent.dequeue;
			this.callback && this.callback.call(this.target);
			if (this.parent) {
				var m = this.parent;
				m.isMoving = 0;
				m.dequeue();
			}
			f.remove(this);
		} else {
			for (var l = 0, h = o.length, n; l < h; l++) {
				n = o[l];
				var g = this.tweenType(k - this.start, this.begin[n], this.change[n], this.duration);
				if (this.setProperty) {
					this.setProperty(this.target, n, g);
				} else {
					this.target[n] = g;
				}
			}
		}
		this.step && this.step.call(this.target);
	}, toString:function () {
		return "[object Fx]";
	}};
	b.Tween = f;
})(window);

