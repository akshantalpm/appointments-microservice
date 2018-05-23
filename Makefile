NAME=appointments-management
VERSION=$(shell cat VERSION)
REPO=poojaak
DEPLOY_DEV_URL=http://${JENKINS_DEV_USER}:${JENKINS_DEV_TOKEN}@18.219.70.127:8080/job/appt-mgmt-deploy-dev/build


build:
	docker build -t $(REPO)/$(NAME):$(VERSION) .

tag-latest:
	docker tag $(REPO)/$(NAME):$(VERSION) $(REPO)/$(NAME):latest

tag-develop: build
	docker tag $(REPO)/$(NAME):$(VERSION) $(REPO)/$(NAME):develop
	docker push $(REPO)/$(NAME):develop


run: build
	docker run \
	-p 8080:8080 \
	-t $(REPO)/$(NAME):$(VERSION)

install: build
	docker push $(REPO)/$(NAME):$(VERSION)

deploy:
	docker tag $(REPO)/$(NAME):develop registry.heroku.com/$(NAME)/web
	@curl -vvv -XPOST "${DEPLOY_DEV_URL}"

