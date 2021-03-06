/*************************************************************************
 * Copyright 2008 Regents of the University of California
 * Copyright 2009-2012 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 * THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 * COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 * AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 * IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 * SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 * WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 * REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 * IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 * NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

@GroovyAddClassUUID
package com.eucalyptus.notifications;

import com.eucalyptus.component.annotation.ComponentMessage
import com.eucalyptus.component.id.Notifications
import edu.ucsb.eucalyptus.msgs.BaseMessage
import edu.ucsb.eucalyptus.msgs.EucalyptusData
import edu.ucsb.eucalyptus.msgs.GroovyAddClassUUID

@ComponentMessage(Notifications.class)
public class NotificationMessage extends BaseMessage {
}
public class Subscription extends EucalyptusData {
  String subscriptionArn;
  String owner;
  String protocol;
  String endpoint;
  String topicArn;
  public Subscription() {  }
}
public class ListSubscriptionsByTopicType extends NotificationMessage {
  String topicArn;
  String nextToken;
  public ListSubscriptionsByTopicType() {  }
}
public class UnsubscribeType extends NotificationMessage {
  String subscriptionArn;
  public UnsubscribeType() {  }
}
public class SetTopicAttributesType extends NotificationMessage {
  String topicArn;
  String attributeName;
  String attributeValue;
  public SetTopicAttributesType() {  }
}
public class AddPermissionResponseType extends NotificationMessage {
  public AddPermissionResponseType() {  }
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ErrorDetail extends EucalyptusData {
  public ErrorDetail() {  }
}
public class ListTopicsResult extends EucalyptusData {
  TopicsList topics;
  String nextToken;
  public ListTopicsResult() {  }
}
public class GetTopicAttributesResult extends EucalyptusData {
  TopicAttributesMap attributes;
  public GetTopicAttributesResult() {  }
}
public class Topic extends EucalyptusData {
  String topicArn;
  public Topic() {  }
}
public class SubscribeResponseType extends NotificationMessage {
  public SubscribeResponseType() {  }
  SubscribeResult subscribeResult = new SubscribeResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ConfirmSubscriptionType extends NotificationMessage {
  String topicArn;
  String token;
  String authenticateOnUnsubscribe;
  public ConfirmSubscriptionType() {  }
}
public class TopicAttributesMapEntry extends EucalyptusData {
  String key;
  String value;
  public TopicAttributesMapEntry() {  }
}
public class ListTopicsType extends NotificationMessage {
  String nextToken;
  public ListTopicsType() {  }
}
public class CreateTopicResponseType extends NotificationMessage {
  public CreateTopicResponseType() {  }
  CreateTopicResult createTopicResult = new CreateTopicResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ConfirmSubscriptionResult extends EucalyptusData {
  String subscriptionArn;
  public ConfirmSubscriptionResult() {  }
}
public class RemovePermissionType extends NotificationMessage {
  String topicArn;
  String label;
  public RemovePermissionType() {  }
}
public class CreateTopicType extends NotificationMessage {
  String name;
  public CreateTopicType() {  }
}
public class SubscriptionsList extends EucalyptusData {
  public SubscriptionsList() {  }
  ArrayList<Subscription> member = new ArrayList<Subscription>();
}
public class DelegatesList extends EucalyptusData {
  public DelegatesList() {  }
  ArrayList<String> member = new ArrayList<String>();
}
public class CreateTopicResult extends EucalyptusData {
  String topicArn;
  public CreateTopicResult() {  }
}
public class GetTopicAttributesResponseType extends NotificationMessage {
  public GetTopicAttributesResponseType() {  }
  GetTopicAttributesResult getTopicAttributesResult = new GetTopicAttributesResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class RemovePermissionResponseType extends NotificationMessage {
  public RemovePermissionResponseType() {  }
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class GetTopicAttributesType extends NotificationMessage {
  String topicArn;
  public GetTopicAttributesType() {  }
}
public class DeleteTopicType extends NotificationMessage {
  String topicArn;
  public DeleteTopicType() {  }
}
public class ListSubscriptionsResponseType extends NotificationMessage {
  public ListSubscriptionsResponseType() {  }
  ListSubscriptionsResult listSubscriptionsResult = new ListSubscriptionsResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class SubscribeResult extends EucalyptusData {
  String subscriptionArn;
  public SubscribeResult() {  }
}
public class SetTopicAttributesResponseType extends NotificationMessage {
  public SetTopicAttributesResponseType() {  }
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ErrorResponse extends EucalyptusData {
  String requestId;
  public ErrorResponse() {  }
  ArrayList<Error> error = new ArrayList<Error>();
}
public class ConfirmSubscriptionResponseType extends NotificationMessage {
  public ConfirmSubscriptionResponseType() {  }
  ConfirmSubscriptionResult confirmSubscriptionResult = new ConfirmSubscriptionResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ResponseMetadata extends EucalyptusData {
  String requestId;
  public ResponseMetadata() {  }
}
public class ListSubscriptionsResult extends EucalyptusData {
  SubscriptionsList subscriptions;
  String nextToken;
  public ListSubscriptionsResult() {  }
}
public class AddPermissionType extends NotificationMessage {
  String topicArn;
  String label;
  DelegatesList awsAccountId;
  ActionsList actionName;
  public AddPermissionType() {  }
}
public class DeleteTopicResponseType extends NotificationMessage {
  public DeleteTopicResponseType() {  }
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ActionsList extends EucalyptusData {
  public ActionsList() {  }
  ArrayList<String> member = new ArrayList<String>();
}
public class TopicAttributesMap extends EucalyptusData {
  public TopicAttributesMap() {  }
  ArrayList<TopicAttributesMapEntry> entry = new ArrayList<TopicAttributesMapEntry>();
}
public class UnsubscribeResponseType extends NotificationMessage {
  public UnsubscribeResponseType() {  }
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ListSubscriptionsByTopicResult extends EucalyptusData {
  SubscriptionsList subscriptions;
  String nextToken;
  public ListSubscriptionsByTopicResult() {  }
}
public class PublishResult extends EucalyptusData {
  String messageId;
  public PublishResult() {  }
}
public class Error extends EucalyptusData {
  String type;
  String code;
  String message;
  public Error() {  }
  ErrorDetail detail = new ErrorDetail();
}
public class PublishResponseType extends NotificationMessage {
  public PublishResponseType() {  }
  PublishResult publishResult = new PublishResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class ListSubscriptionsType extends NotificationMessage {
  String nextToken;
  public ListSubscriptionsType() {  }
}
public class PublishType extends NotificationMessage {
  String topicArn;
  String message;
  String subject;
  public PublishType() {  }
}
public class ListSubscriptionsByTopicResponseType extends NotificationMessage {
  public ListSubscriptionsByTopicResponseType() {  }
  ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = new ListSubscriptionsByTopicResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class TopicsList extends EucalyptusData {
  public TopicsList() {  }
  ArrayList<Topic> member = new ArrayList<Topic>();
}
public class ListTopicsResponseType extends NotificationMessage {
  public ListTopicsResponseType() {  }
  ListTopicsResult listTopicsResult = new ListTopicsResult();
  ResponseMetadata responseMetadata = new ResponseMetadata();
}
public class SubscribeType extends NotificationMessage {
  String topicArn;
  String protocol;
  String endpoint;
  public SubscribeType() {  }
}
